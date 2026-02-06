package com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.exception;

public class InvalidEmployeeDataException extends RuntimeException {

    public InvalidEmployeeDataException(String message) {
        super(message);
    }
}
