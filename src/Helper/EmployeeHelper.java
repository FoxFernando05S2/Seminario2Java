/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import Model.Entities.Employee;
import view.EmployeeView;

/**
 *
 * @author Usuario
 */
public class EmployeeHelper {

    public static Employee fromForm(EmployeeView view) {
        String id = view.txtEmployeeId.getText().trim();
        String userId = view.txtUserId.getText().trim();
        String position = (String) view.cmbPosition.getSelectedItem();

        double salary = 0;
        try {
            salary = Double.parseDouble(view.txtSalary.getText().trim());
        } catch (NumberFormatException e) {
            salary = -1; // marcar como inválido si es necesario
        }

        return new Employee(id, userId, position, salary);
    }
}
