package sk.ukf.employees.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sk.ukf.employees.entity.Employee;

import java.util.Optional;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByEmail(String email);

    @Query("SELECT DISTINCT e.jobTitle FROM Employee e ORDER BY e.jobTitle")
    List<String> findAllJobTitles();
}