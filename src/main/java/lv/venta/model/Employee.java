package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "EmployeeTable")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDE")
    private long ide;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50)
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    @Size(min = 2, max = 50)
    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @NotBlank(message = "Role cannot be blank")
    @Column(name = "ROLE", nullable = false)
    private String role;

    @Email(message = "Must be a valid email")
    @Column(name = "EMAIL")
    private String email;

    // Many employees work in one department
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDLD", nullable = false)
    private LibraryDepartment libraryDepartment;

    // --- Constructors ---

    public Employee() {}

    public Employee(String name, String surname, String role,
                    String email, LibraryDepartment libraryDepartment) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.email = email;
        this.libraryDepartment = libraryDepartment;
    }

    // --- Getters & Setters ---

    public long getIde() { return ide; }
    public void setIde(long ide) { this.ide = ide; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LibraryDepartment getLibraryDepartment() { return libraryDepartment; }
    public void setLibraryDepartment(LibraryDepartment libraryDepartment) {
        this.libraryDepartment = libraryDepartment;
    }

    @Override
    public String toString() {
        return "Employee{ide=" + ide + ", name='" + name + "', surname='" + surname
                + "', role='" + role + "', email='" + email + "'}";
    }
}