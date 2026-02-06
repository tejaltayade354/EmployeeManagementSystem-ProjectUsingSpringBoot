package com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter
@NoArgsConstructor
public class FullTimeEmployee extends Employee{

    private double monthlySalary;

    @Override
    public double calculateSal(){
        return monthlySalary;
    }
}
