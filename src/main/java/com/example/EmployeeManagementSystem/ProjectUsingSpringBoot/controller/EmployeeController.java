package com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.controller;

import com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.exception.EmployeeNotFoundException;
import com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.model.Employee;
import com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.model.FullTimeEmployee;
import com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.model.PartTimeEmployee;
import com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @RestController
    @RequestMapping("/api/employees")
    public class EmployeeController {

        private final EmployeeService service1;

        public EmployeeController(EmployeeService service) {
            this.service1 = service;
        }


        // CREATE
        @PostMapping("/fulltime")
        public Employee addFullTime(@RequestBody FullTimeEmployee emp) {
            return service1.addEmployee(emp);
        }

        @PostMapping("/parttime")
        public Employee addPartTime(@RequestBody PartTimeEmployee emp) {
            return service1.addEmployee(emp);
        }

//    // READ
//    @GetMapping
//    public  List<Employee> getAll() {
//        return service1.getAllEmployees();
//    }

        @GetMapping("/sorted")
        public List<Employee> getSortedEmployees(
                @RequestParam(defaultValue = "name") String sortBy,
                @RequestParam(defaultValue = "asc") String direction
        ) {
            return service1.getAllEmployeesSorted(sortBy, direction);
        }

        @GetMapping("/sorted-by-salary")
        public List<Employee> getSortedBySalary(
                @RequestParam(defaultValue = "asc") String direction
        ) {
            return service1.getEmployeesSortedBySalary(direction);
        }


        @GetMapping("/getEmployee/{id}")
        public Employee getById(@PathVariable Long id) throws EmployeeNotFoundException {
            return service1.getEmployeeById(id);
        }

        // DELETE
        @DeleteMapping("deleteEmployee/{id}")
        public String delete(@PathVariable Long id) {
            service1.deleteEmployee(id);
            return "Employee deleted successfully";
        }

        //    @PutMapping("/{id}")
//    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
//            return service1.updateEmployee(id, employee);
//        }
        @PutMapping("/part-time/{id}")
        public ResponseEntity<PartTimeEmployee> updatePartTimeEmployee(
                @PathVariable Long id,
                @RequestBody PartTimeEmployee employee) {

            PartTimeEmployee updated =
                    service1.updateEmployee(id, employee);

            return ResponseEntity.ok(updated);
        }

    }