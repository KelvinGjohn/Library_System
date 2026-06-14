package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LibraryDepartmentTable")
public class LibraryDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDLD")
    private long idld;

    @NotBlank(message = "Specialization cannot be blank")
    @Size(min = 2, max = 100)
    @Column(name = "SPECIALIZATION", nullable = false)
    private String specialization;

    @NotBlank(message = "Employees info cannot be blank")
    @Column(name = "EMPLOYEES", nullable = false)
    private String employees;

    @NotBlank(message = "Working times cannot be blank")
    @Column(name = "WORKING_TIMES", nullable = false)
    private String workingTimes;

    // One department has many books (booklist)
    @OneToMany(mappedBy = "libraryDepartment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> bookList = new ArrayList<>();

    // --- Constructors ---

    public LibraryDepartment() {}

    public LibraryDepartment(String specialization, String employees, String workingTimes) {
        this.specialization = specialization;
        this.employees = employees;
        this.workingTimes = workingTimes;
    }

    // giveBook() – mark a checkout as returned (book given back)
    public void giveBook(Checkout checkout) {
        checkout.setReturned(true);
    }

    // takeBook() – create a checkout linking reader + book
    public void takeBook(Reader reader, Book book, Checkout checkout) {
        checkout.setReader(reader);
        checkout.setBook(book);
        checkout.setReturned(false);
    }

    // addBook() – add a book to this department
    public void addBook(Book book) {
        book.setLibraryDepartment(this);
        this.bookList.add(book);
    }

    // removeBook() – remove a book from the department list
    public void removeBook(Book book) {
        this.bookList.remove(book);
    }

    // --- Getters & Setters ---

    public long getIdld() { return idld; }
    public void setIdld(long idld) { this.idld = idld; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getEmployees() { return employees; }
    public void setEmployees(String employees) { this.employees = employees; }

    public String getWorkingTimes() { return workingTimes; }
    public void setWorkingTimes(String workingTimes) { this.workingTimes = workingTimes; }

    public List<Book> getBookList() { return bookList; }
    public void setBookList(List<Book> bookList) { this.bookList = bookList; }

    @Override
    public String toString() {
        return "LibraryDepartment{idld=" + idld + ", specialization='" + specialization
                + "', employees='" + employees + "', workingTimes='" + workingTimes + "'}";
    }
}