package controller;

import Helper.EmployeeHelper;
import Model.Entities.Employee;
import Model.Dao.EmployeeDAO;
import Model.validation.EmployeeValidator;
import view.EmployeeView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.util.List;

public class EmployeeController {

    private EmployeeView view;
    private EmployeeDAO employeeDAO;

    public EmployeeController(EmployeeView view, EmployeeDAO employeeDAO) {
        this.view = view;
        this.employeeDAO = employeeDAO;

        loadEmployeeTable();
        addActionListeners();
    }

    private void addActionListeners() {
        view.btnAdd.addActionListener(e -> addEmployee());
        view.btnUpdate.addActionListener(e -> updateEmployee());
        view.btnDelete.addActionListener(e -> deleteEmployee());
        view.btnShow.addActionListener(e -> showEmployee());

        view.employeeTable.getSelectionModel().addListSelectionListener(this::fillFormFromTable);
    }

    private void addEmployee() {
        Employee emp = EmployeeHelper.fromForm(view);
        String error = EmployeeValidator.validate(emp);
        if (error != null) {
            JOptionPane.showMessageDialog(view, error);
            return;
        }

        if (employeeDAO.getById(emp.getEmployeeId()) != null) {
            JOptionPane.showMessageDialog(view, "Employee ID already exists.");
            return;
        }

        if (employeeDAO.insert(emp)) {
            JOptionPane.showMessageDialog(view, "Employee added successfully.");
            loadEmployeeTable();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(view, "Error adding employee.");
        }
    }

    private void updateEmployee() {
        Employee employee = EmployeeHelper.fromForm(view);

        String validationMessage = EmployeeValidator.validate(employee);
        if (validationMessage != null) {
            JOptionPane.showMessageDialog(view, validationMessage);
            return;
        }

        if (employeeDAO.getById(employee.getEmployeeId()) == null) {
            JOptionPane.showMessageDialog(view, "Employee ID does not exist.");
            return;
        }

        if (employeeDAO.update(employee)) {
            JOptionPane.showMessageDialog(view, "Employee updated successfully.");
            loadEmployeeTable();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(view, "Error updating employee.");
        }
    }

    private void deleteEmployee() {
        String employeeId = view.txtEmployeeId.getText().trim();

        if (employeeId.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter the Employee ID to delete.");
            return;
        }

        if (employeeDAO.getById(employeeId) == null) {
            JOptionPane.showMessageDialog(view, "Employee ID does not exist.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this employee?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION && employeeDAO.delete(employeeId)) {
            JOptionPane.showMessageDialog(view, "Employee deleted successfully.");
            loadEmployeeTable();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(view, "Error deleting employee.");
        }
    }

    private void showEmployee() {
        String employeeId = view.txtEmployeeId.getText().trim();

        if (employeeId.isEmpty()) {
            loadEmployeeTable();
            return;
        }

        Employee employee = employeeDAO.getById(employeeId);
        if (employee != null) {
            view.updateEmployeeTable(
                    new Object[][]{
                        {employee.getEmployeeId(), employee.getUserId(), employee.getPosition(), employee.getSalary()}
                    },
                    new String[]{"Employee ID", "User ID", "Position", "Salary"}
            );
        } else {
            JOptionPane.showMessageDialog(view, "Employee not found.");
            loadEmployeeTable();
        }
    }

    private void loadEmployeeTable() {
        List<Employee> employees = employeeDAO.getAll();
        Object[][] data = employees.stream()
                .map(emp -> new Object[]{emp.getEmployeeId(), emp.getUserId(), emp.getPosition(), emp.getSalary()})
                .toArray(Object[][]::new);

        String[] columnNames = {"Employee ID", "User ID", "Position", "Salary"};
        view.updateEmployeeTable(data, columnNames);
    }

    private void fillFormFromTable(ListSelectionEvent e) {
        int selectedRow = view.employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            view.txtEmployeeId.setText(view.employeeTable.getValueAt(selectedRow, 0).toString());
            view.txtUserId.setText(view.employeeTable.getValueAt(selectedRow, 1).toString());
            view.cmbPosition.setSelectedItem(view.employeeTable.getValueAt(selectedRow, 2).toString());
            view.txtSalary.setText(view.employeeTable.getValueAt(selectedRow, 3).toString());
        }
    }

    private void clearForm() {
        view.txtEmployeeId.setText("");
        view.txtUserId.setText("");
        view.cmbPosition.setSelectedIndex(0);
        view.txtSalary.setText("");
        view.employeeTable.clearSelection();
    }
}
