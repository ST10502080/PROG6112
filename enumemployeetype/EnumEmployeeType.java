/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.enumemployeetype;
import java.util.*;
/**
 *
 * @author Az'ulwazi
 */
public class EnumEmployeeType {

    public static void main(String[] args) {
enum EmployeeType {
    FIXED,
    PERMANENT,
    TEMPORARY
}

// Step 2: Create the Employee class
class Employee {
    // Fields (attributes)
    public int employeeNumber;
    public String name;
    public String identityNumber;
    public EmployeeType type;   // Using the enum from Step 1

    // Constructor to initialize all fields
    public Employee(int employeeNumber, String name, String identityNumber, EmployeeType type) {
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.identityNumber = identityNumber;
        this.type = type;
    }

    // Method to display employee data
    public void displayInfo() {
        System.out.println("Employee Number  : " + employeeNumber);
        System.out.println("Name             : " + name);
        System.out.println("Identity Number  : " + identityNumber);
        System.out.println("Appointment Type : " + type);
        System.out.println("----------------------------------");
    }
}

// Step 3: Main program to test everything


        // Create an array of Employee objects
        Employee[] employees = new Employee[3];

        // Fill the array with different employee types
        employees[0] = new Employee(101, "Alice Smith", "ID-1001", EmployeeType.FIXED);
        employees[1] = new Employee(102, "Bob Johnson", "ID-1002", EmployeeType.PERMANENT);
        employees[2] = new Employee(103, "Carol White", "ID-1003", EmployeeType.TEMPORARY);

        // Print out each employee's data
        System.out.println("===== EMPLOYEE DETAILS =====\n");
        for (Employee emp : employees) {
            emp.displayInfo();
        }
    }
}
