package sk.ukf.employees.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Pole nesmie byť prázdne")
    private String firstName;

    @NotBlank(message = "Pole nesmie byť prázdne")
    private String lastName;

    @NotNull(message = "Pole je povinné")
    private LocalDate birthDate;

    @NotBlank(message = "Pole je povinné")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Neplatný formát emailu, skontrolujte ešte raz"
    )
    private String email;

    @Pattern(
            regexp = "^\\+[0-9]{1,3}[0-9]{6,14}$",
            message = "Telefónne číslo musí začínať +, obsahovať kód krajiny a 6–14 číslic"
    )
    private String phone;

    @NotBlank(message = "Pole je povinné")
    private String jobTitle;

    @NotNull(message = "Pole je povinné")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be positive")
    private Double salary;

    private Boolean fullTime;

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

    public Boolean getFullTime() {
        return fullTime;
    }

    public void setFullTime(Boolean fullTime) {
        this.fullTime = fullTime;
    }
}
