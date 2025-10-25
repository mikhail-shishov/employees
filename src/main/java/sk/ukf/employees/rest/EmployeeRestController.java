package sk.ukf.employees.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import sk.ukf.employees.entity.Employee;
import sk.ukf.employees.service.EmployeeService;
import sk.ukf.employees.dto.ApiResponse;
import java.time.LocalDate;
import java.util.Map;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<ApiResponse<List<Employee>>> findAll() {
        List<Employee> employees = employeeService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(employees, "List of employees", LocalDateTime.now()));
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<ApiResponse<Employee>> getEmployee(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Employee id not found - " + id));
        }
        return ResponseEntity.ok(ApiResponse.success(employee, "Employee found"));
    }

    @PostMapping("/employees")
    public ResponseEntity<ApiResponse<Employee>> addEmployee(@Valid @RequestBody Employee employee) {
        employee.setId(0);
        Employee saved = employeeService.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(saved, "Employee created successfully"));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(@PathVariable int id, @Valid @RequestBody Employee employee) {
        Employee existing = employeeService.findById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Employee id not found - " + id));
        }
        employee.setId(id);
        Employee updated = employeeService.save(employee);
        return ResponseEntity.ok(ApiResponse.success(updated, "Employee updated successfully"));
    }

    @PatchMapping("/employees/{id}")
    public ResponseEntity<ApiResponse<Employee>> patchEmployee(
            @PathVariable int id,
            @RequestBody Map<String, Object> updates) {

        Employee existing = employeeService.findById(id);

        updates.forEach((key, value) -> {
            switch (key) {
                case "firstName" -> existing.setFirstName((String) value);
                case "lastName" -> existing.setLastName((String) value);
                case "birthDate" -> existing.setBirthDate(LocalDate.parse((String) value));
                case "email" -> existing.setEmail((String) value);
                case "phone" -> existing.setPhone((String) value);
                case "jobTitle" -> existing.setJobTitle((String) value);
                case "salary" -> existing.setSalary(Double.valueOf(value.toString()));
                case "fullTime" -> existing.setFullTime(Boolean.valueOf(value.toString()));
            }
        });

        Employee updated = employeeService.save(existing);
        return ResponseEntity.ok(ApiResponse.success(updated, "Employee partially updated"));
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable int id) {
        Employee existing = employeeService.findById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Employee id not found - " + id));
        }
        employeeService.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success("Deleted employee id - " + id, "Employee deleted successfully"));
    }
}
