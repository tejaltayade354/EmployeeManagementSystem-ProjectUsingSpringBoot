package com.example.EmployeeManagementSystem.ProjectUsingSpringBoot.exception;

public class EmployeeNotFoundException extends RuntimeException {
   public EmployeeNotFoundException(String message){
       super(message);
   }
}
