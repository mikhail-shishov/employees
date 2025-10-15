package sk.ukf.employees.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.ukf.employees.entity.Employee;

import java.util.List;

//@Repository
//public class EmployeeDAOJpaImpl implements EmployeeDAO {
//    private EntityManager entityManager;
//
//    @Autowired
//    public EmployeeDAOJpaImpl(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//    @Override
//    public List<Employee> findAll() {
//        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
//        List<Employee> employees = query.getResultList();
//        return employees;
//    }
//}

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
        List<Employee> employees = query.getResultList();
        return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee employees = entityManager.find(Employee.class, id);
        return employees;
    }

    @Override
    public Employee save(Employee employees) {
        Employee employee_db = entityManager.merge(employees);
        return employee_db;
    }

    @Override
    public void deleteById(int id) {
        Employee employees = entityManager.find(Employee.class, id);
        entityManager.remove(employees);
    }
}