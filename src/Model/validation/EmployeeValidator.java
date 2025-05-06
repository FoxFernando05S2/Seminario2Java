/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.validation;

import Model.Entities.Employee;

/**
 *
 * @author Usuario
 */
public class EmployeeValidator {
    public static String validate(Employee emp) {
        if (emp.getEmployeeId().isEmpty() || emp.getUserId().isEmpty()) {
            return "Employee ID and User ID are required.";
        }

        if (emp.getSalary() < 1160) {
            return "Salary must be above minimum wage.";
        }

        if (emp.getPosition() == null || emp.getPosition().trim().isEmpty()) {
            return "Position is required.";
        }

        return null; // valid
    }
}
