package com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PartTimeEmployee extends Employee {

    private int hoursWorked;
    private double hourlyRate;

    @Override
    public double calculateSal() {
        return hoursWorked * hourlyRate;
    }
}
