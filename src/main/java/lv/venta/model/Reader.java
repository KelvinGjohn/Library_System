package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ReaderTable")
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDR")
    private long idr;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50)
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    @Size(min = 2, max = 50)
    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @NotBlank(message = "Library user number cannot be blank")
    @Column(name = "LIBRARY_USER_NUMBER", nullable = false, unique = true)
    private String libraryUserNumber;

    // One reader can have many checkouts (their currentTakenBookList + loadHistory)
    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Checkout> checkouts = new ArrayList<>();

    // --- Constructors ---

    public Reader() {}

    public Reader(String name, String surname, String libraryUserNumber) {
        this.name = name;
        this.surname = surname;
        this.libraryUserNumber = libraryUserNumber;
    }

    // loadHistory() – returns past (returned) checkouts
    public List<Checkout> loadHistory() {
        List<Checkout> history = new ArrayList<>();
        for (Checkout c : checkouts) {
            if (c.isReturned()) {
                history.add(c);
            }
        }
        return history;
    }

    // currentTakenBookList – returns active (not returned) checkouts
    public List<Checkout> getCurrentTakenBookList() {
        List<Checkout> current = new ArrayList<>();
        for (Checkout c : checkouts) {
            if (!c.isReturned()) {
                current.add(c);
            }
        }
        return current;
    }

    // --- Getters & Setters ---

    public long getIdr() { return idr; }
    public void setIdr(long idr) { this.idr = idr; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getLibraryUserNumber() { return libraryUserNumber; }
    public void setLibraryUserNumber(String libraryUserNumber) {
        this.libraryUserNumber = libraryUserNumber;
    }

    public List<Checkout> getCheckouts() { return checkouts; }
    public void setCheckouts(List<Checkout> checkouts) { this.checkouts = checkouts; }

    @Override
    public String toString() {
        return "Reader{idr=" + idr + ", name='" + name + "', surname='" + surname
                + "', libraryUserNumber='" + libraryUserNumber + "'}";
    }
}