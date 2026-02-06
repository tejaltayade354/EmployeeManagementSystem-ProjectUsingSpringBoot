package com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.service;

import com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.exception.EmployeeNotFoundException;
import com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.exception.InvalidEmployeeDataException;
import com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.model.Employee;
import com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.model.FullTimeEmployee;
import com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.model.PartTimeEmployee;
import com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.repository.EmployeeRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    // ✅ CORRECT constructor injection
    public EmployeeService(EmployeeRepository repository) {
        this.repository =repository;
    }
    // CREATE
    public Employee addEmployee(Employee employee) {

        String name = employee.getName();
        name = name.trim().replaceAll("\\s+", " ");

        if (name.isEmpty()) {
            throw new InvalidEmployeeDataException("Employee name cannot be empty");
        }

        if (!name.matches("[a-zA-Z ]+")) {
            throw new InvalidEmployeeDataException("Name must not contain special characters");
        }

        employee.setName(name);

        return repository.save(employee);
    }

    // SORT BY FIELD (name / id)
    public List<Employee> getAllEmployeesSorted(String sortBy, String direction) {

        Set<String> allowedFields = Set.of("name", "id");

        if (!allowedFields.contains(sortBy)) {
            throw new InvalidEmployeeDataException("Invalid sort field: " + sortBy);
        }

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        return repository.findAll(sort);
    }

    // SORT BY CALCULATED SALARY
    public List<Employee> getEmployeesSortedBySalary(String direction) {

        Comparator<Employee> comparator =
                Comparator.comparingDouble(Employee::calculateSal);

        if (direction.equalsIgnoreCase("desc")) {
            comparator = comparator.reversed();
        }

        return repository.findAll()
                .stream()
                .sorted(comparator)
                .toList();
    }

    // READ BY ID
    public Employee getEmployeeById(Long id) throws EmployeeNotFoundException {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee not found with id: " + id)
                );
    }

    // DELETE
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }


    public PartTimeEmployee updateEmployee(Long id, PartTimeEmployee employee) {
        Employee existing = repository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id: " + id)
                );

        // 🚫 Prevent updating full-time employee
        if (!(existing instanceof PartTimeEmployee)) {
            throw new InvalidEmployeeDataException(
                    "Employee with id " + id + " is not a Part-Time employee");
        }

        PartTimeEmployee existingPt = (PartTimeEmployee) existing;

        // 🔥 Name validation & cleanup
        String name = employee.getName();

        if (name == null) {
            throw new InvalidEmployeeDataException("Name cannot be null");
        }

        name = name.trim().replaceAll("\\s+", " ");

        if (name.isEmpty()) {
            throw new InvalidEmployeeDataException("Name cannot be empty");
        }

        if (!name.matches("[a-zA-Z ]+")) {
            throw new InvalidEmployeeDataException(
                    "Name must contain only alphabets and spaces");
        }

        // 🔄 Update fields
        existingPt.setName(name);
        existingPt.setHoursWorked(employee.getHoursWorked());
        existingPt.setHourlyRate(employee.getHourlyRate());

        return repository.save(existingPt);
    }
}
