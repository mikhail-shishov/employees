package sk.ukf.employees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.ukf.employees.dao.EmployeeRepository;
import sk.ukf.employees.entity.Employee;
import sk.ukf.employees.exception.EmailAlreadyExistsException;
import sk.ukf.employees.exception.ObjectNotFoundException;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Employee", id));
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        employeeRepository.findByEmail(employee.getEmail()).filter(e -> e.getId() != employee.getId()).ifPresent(e -> {
            throw new EmailAlreadyExistsException(employee.getEmail());
        });

        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        if (!employeeRepository.existsById(id)) {
            throw new ObjectNotFoundException("Employee", id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public List<String> findAllJobTitles() {
        return employeeRepository.findAllJobTitles();
    }
}
