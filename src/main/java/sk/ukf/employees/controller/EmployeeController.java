package sk.ukf.employees.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sk.ukf.employees.entity.Employee;
import sk.ukf.employees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Value("${job.titles}")
    private String jobTitlesProperty;

    @Value("${job.worktypes}")
    private String jobWorkTypesProperty;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employees/list";
    }

    @GetMapping("/{id}")
    public String viewEmployee(@PathVariable int id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "employees/view";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("jobTitles", Arrays.asList(jobTitlesProperty.split("\\s*,\\s*")));
        model.addAttribute("jobWorkTypes", Arrays.asList(jobWorkTypesProperty.split("\\s*,\\s*")));
        return "employees/form";
    }

    @PostMapping
    public String createEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("jobTitles", Arrays.asList(jobTitlesProperty.split("\\s*,\\s*")));
            model.addAttribute("jobWorkTypes", Arrays.asList(jobWorkTypesProperty.split("\\s*,\\s*")));
            return "employees/form";
        }

        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("jobTitles", Arrays.asList(jobTitlesProperty.split("\\s*,\\s*")));
        model.addAttribute("jobWorkTypes", Arrays.asList(jobWorkTypesProperty.split("\\s*,\\s*")));
        return "employees/form";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }
}