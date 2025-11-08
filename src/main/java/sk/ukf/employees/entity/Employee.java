package sk.ukf.employees.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Meno nesmie byť prázdne")
    @Size(min = 2, max = 64, message = "Meno musí mať 2 až 64 znakov")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Priezvisko nesmie byť prázdne")
    @Size(min = 2, max = 64, message = "Priezvisko musí mať 2 až 64 znakov")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Pole je povinné")
    @Past(message = "Dátum narodenia musí byť v minulosti")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotBlank(message = "Email nesmie byť prázdny")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Email musí mať platný formát, napr. mail@example.com")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Telefónne číslo je povinné")
    @Size(max = 14, message = "Telefónne číslo môže mať najviac 20 znakov")
    @Pattern(regexp = "^\\+[0-9]{1,3}[0-9]{6,14}$", message = "Telefónne číslo musí začínať +, obsahovať kód krajiny a 6–14 číslic")
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Pracovná pozícia nesmie byť prázdna")
    @Column(name = "job_title")
    private String jobTitle;

    @NotNull(message = "Pole je povinné")
    @DecimalMin(value = "0.0", message = "Plat nesmie byť zaporný")
    private Double salary;

    @NotBlank(message = "Typ úvazku je povinný")
    private String fullTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getFullTime() {
        return fullTime;
    }

    public void setFullTime(String fullTime) {
        this.fullTime = fullTime;
    }
}
