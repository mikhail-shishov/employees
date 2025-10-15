package sk.ukf.employees.dao;

import sk.ukf.employees.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employees);

    void deleteById(int id);
}