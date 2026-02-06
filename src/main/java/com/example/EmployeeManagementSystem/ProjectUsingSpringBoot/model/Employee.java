package com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity                //marks the class as JPA Entity.
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Employee {

    @Id   //- Marks the primary key of the entity

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public abstract double calculateSal();
}
