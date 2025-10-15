package sk.ukf.employees.dao;

import sk.ukf.employees.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
}