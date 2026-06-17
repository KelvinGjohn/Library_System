package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BookTable")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDB")
    private long idb;

    @NotBlank(message = "ISBN cannot be blank")
    @Column(name = "ISBN", nullable = false, unique = true)
    private String isbn;

    @Min(value = 1000, message = "Year must be valid")
    @Max(value = 2100, message = "Year must be valid")
    @Column(name = "YEAR", nullable = false)
    private int year;

    @NotBlank(message = "Author cannot be blank")
    @Size(min = 2, max = 100)
    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 1, max = 200)
    @Column(name = "TITLE", nullable = false)
    private String title;

    @DecimalMin(value = "0.0", message = "Rating must be 0 or greater")
    @DecimalMax(value = "5.0", message = "Rating must be 5 or less")
    @Column(name = "RATING")
    private double rating;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_condition", nullable = false)
    private BookCondition condition;

    @Column(name = "RARITY")
    private String rarity;

    // Many books belong to one LibraryDepartment
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDLD", nullable = false)
    private LibraryDepartment libraryDepartment;

    // A book can be part of many checkouts
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Checkout> checkouts = new ArrayList<>();

   

    public Book() {}

    public Book(String isbn, int year, String author, String title,
                double rating, BookCondition condition, String rarity,
                LibraryDepartment libraryDepartment) {
        this.isbn = isbn;
        this.year = year;
        this.author = author;
        this.title = title;
        this.rating = rating;
        this.condition = condition;
        this.rarity = rarity;
        this.libraryDepartment = libraryDepartment;
    }

   
    public void changeCondition(BookCondition newCondition) {
        this.condition = newCondition;
    }

    // --- Getters & Setters ---

    public long getIdb() { return idb; }
    public void setIdb(long idb) { this.idb = idb; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getYear() { return year; }
    public void setYear(int year) {
    	if (year >0 && year <= 2026) {this.year =year;
    	}else {
    		throw new IllegalArgumentException("invalid year");
    	}
	}

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public void settTitle(String title) {
    	if (title != null && !title.trim().isEmpty()) {
    		this.title = title;
    	} else {
    		this .title = "Unkown";
    	}
    	}
    public void setTitle(String title) { this.title = title; }

    public double getRating() {
    	return rating;
    }
    	
    public void setRating(double rating) { 
    	if(rating >= 0.0 && rating <= 5.0) {
    		this.rating = rating;
    	} else {
    		throw new IllegalArgumentException("Rating must be between 0.0 and 5.0");
    	}
    
    }

    public BookCondition getCondition() { return condition; }
    public void setCondition(BookCondition condition) { this.condition = condition; }

    public String getRarity() { return rarity; }
    
    public String checkRarity() {
        if (this.rarity == null) {
            return "Not Specified";
        } else {
            return this.rarity;
        }
    }

    public LibraryDepartment getLibraryDepartment() { return libraryDepartment; }
    public void setLibraryDepartment(LibraryDepartment libraryDepartment) {
        this.libraryDepartment = libraryDepartment;
    }

    public List<Checkout> getCheckouts() { return checkouts; }
    public void setCheckouts(List<Checkout> checkouts) { this.checkouts = checkouts; }

    @Override
    public String toString() {
        return "Book{idb=" + idb + ", isbn='" + isbn + "', title='" + title
                + "', author='" + author + "', year=" + year
                + ", rating=" + rating + ", condition=" + condition
                + ", rarity='" + rarity + "'}";
    }

	public void setRarity(String rarity2) {
		// TODO Auto-generated method stub
		
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}
}