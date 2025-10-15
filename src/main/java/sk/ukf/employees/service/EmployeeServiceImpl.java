package sk.ukf.employees.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.employees.dao.EmployeeDAO;
import sk.ukf.employees.entity.Employee;

import java.util.List;

//@Service
//public class EmployeeServiceImpl implements EmployeeService {
//    private EmployeeDAO employeeDAO;
//
//    @Autowired
//    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
//        this.employeeDAO = employeeDAO;
//    }
//    @Override
//    public List<Employee> findAll() {
//        return employeeDAO.findAll();
//    }
//}

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }
    @Override
    public Employee findById(int id) {
        return employeeDAO.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }
    @Transactional
    @Override
    public void deleteById(int id) {
        employeeDAO.deleteById(id);
    }
}