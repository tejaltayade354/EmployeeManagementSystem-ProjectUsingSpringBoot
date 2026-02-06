package com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.repository;


import com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
