package sk.ukf.employees.service;

import sk.ukf.employees.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
}
