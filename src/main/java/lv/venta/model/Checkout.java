package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "CheckoutTable")
public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDC")
    private long idc;

    @Column(name = "CHECKOUT_DATE", nullable = false)
    private LocalDate checkoutDate;

    @Column(name = "DUE_DATE")
    private LocalDate dueDate;

    @Column(name = "IS_RETURNED", nullable = false)
    private boolean returned;

    // Many checkouts → one reader
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDR", nullable = false)
    private Reader reader;

    // Many checkouts → one book
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDB", nullable = false)
    private Book book;

    // --- Constructors ---

    public Checkout() {}

    public Checkout(LocalDate checkoutDate, LocalDate dueDate,
                    boolean returned, Reader reader, Book book) {
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.returned = returned;
        this.reader = reader;
        this.book = book;
    }

    // --- Getters & Setters ---

    public long getIdc() { return idc; }
    public void setIdc(long idc) { this.idc = idc; }

    public LocalDate getCheckoutDate() { return checkoutDate; }
    public void setCheckoutDate(LocalDate checkoutDate) { this.checkoutDate = checkoutDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public boolean isReturned() { return returned; }
    public void setReturned(boolean returned) { this.returned = returned; }

    public Reader getReader() { return reader; }
    public void setReader(Reader reader) { this.reader = reader; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    @Override
    public String toString() {
        return "Checkout{idc=" + idc + ", checkoutDate=" + checkoutDate
                + ", dueDate=" + dueDate + ", returned=" + returned + "}";
    }
}