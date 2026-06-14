package lv.venta.controller;

import lv.venta.model.*;
import lv.venta.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Autowired private ILibraryDepartmentRepo deptRepo;
    @Autowired private IBookRepo bookRepo;
    @Autowired private IReaderRepo readerRepo;
    @Autowired private ICheckoutRepo checkoutRepo;
    @Autowired private IEmployeeRepo employeeRepo;

    @Bean
    public CommandLineRunner createDataForTesting() {
        return args -> {

            // --- Departments (min 2) ---
            LibraryDepartment d1 = deptRepo.save(
                    new LibraryDepartment("Science & Technology", "Alice Johnson, Bob Smith", "Mon-Fri 09:00-17:00"));
            LibraryDepartment d2 = deptRepo.save(
                    new LibraryDepartment("Arts & Literature", "Clara Davis", "Mon-Sat 10:00-18:00"));

            // --- Books (min 2) ---
            Book b1 = bookRepo.save(new Book("978-0-06-112008-4", 1960,
                    "Harper Lee", "To Kill a Mockingbird",
                    4.8, BookCondition.GOOD, "Common", d2));
            Book b2 = bookRepo.save(new Book("978-0-14-028329-7", 1949,
                    "George Orwell", "1984",
                    4.7, BookCondition.NEW, "Common", d2));
            Book b3 = bookRepo.save(new Book("978-0-14-118776-1", 1925,
                    "F. Scott Fitzgerald", "The Great Gatsby",
                    4.3, BookCondition.FAIR, "Rare", d2));
            Book b4 = bookRepo.save(new Book("978-0-07-142822-9", 2001,
                    "Richard Feynman", "Surely You're Joking",
                    4.5, BookCondition.GOOD, "Common", d1));

            // --- Readers (min 2) ---
            Reader r1 = readerRepo.save(new Reader("John", "Smith", "LIB-0001"));
            Reader r2 = readerRepo.save(new Reader("Maria", "Garcia", "LIB-0002"));

            // --- Checkouts (min 2) ---
            checkoutRepo.save(new Checkout(
                    LocalDate.now().minusDays(10),
                    LocalDate.now().plusDays(4),
                    false, r1, b1));
            checkoutRepo.save(new Checkout(
                    LocalDate.now().minusDays(20),
                    LocalDate.now().minusDays(6),
                    true, r1, b2));
            checkoutRepo.save(new Checkout(
                    LocalDate.now().minusDays(5),
                    LocalDate.now().plusDays(9),
                    false, r2, b3));

            // --- Employees (min 2) ---
            employeeRepo.save(new Employee("Alice", "Johnson", "Librarian",
                    "alice@library.lv", d1));
            employeeRepo.save(new Employee("Bob", "Smith", "Senior Librarian",
                    "bob@library.lv", d1));
            employeeRepo.save(new Employee("Clara", "Davis", "Department Head",
                    "clara@library.lv", d2));

            System.out.println(">>> Library test data loaded successfully!");
        };
    }
}