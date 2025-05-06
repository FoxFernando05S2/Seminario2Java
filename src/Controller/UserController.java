package controller;

import Helper.UserHelper;
import Model.Entities.User;
import Model.Dao.UserDAO;
import Model.validation.UserValidator;
import view.UserView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.util.List;

public class UserController {

    private UserView view;
    private UserDAO userDAO;

    public UserController(UserView view, UserDAO userDAO) {
        this.view = view;
        this.userDAO = userDAO;

        loadUserTable();
        addActionListeners();
    }

    private void addActionListeners() {
        view.btnAdd.addActionListener(e -> addUser());
        view.btnUpdate.addActionListener(e -> updateUser());
        view.btnDelete.addActionListener(e -> deleteUser());
        view.btnShow.addActionListener(e -> showUser());

        view.userTable.getSelectionModel().addListSelectionListener(this::fillFormFromTable);
    }

    private void addUser() {
        String id = view.txtUserId.getText().trim();
        String name = view.txtName.getText().trim();
        String email = view.txtEmail.getText().trim();
        String password = view.txtPassword.getText().trim();
        String role = (String) view.cmbRole.getSelectedItem();

        if (UserValidator.isEmpty(id, name, email, password)) {
            JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            return;
        }

        if (!UserValidator.isValidEmail(email)) {
            JOptionPane.showMessageDialog(view, "Invalid email format.");
            return;
        }

        if (!UserValidator.isValidPassword(password)) {
            JOptionPane.showMessageDialog(view, "Password must be at least 6 characters.");
            return;
        }

        User user = new User(id, name, email, password, role);
        if (userDAO.insert(user)) {
            JOptionPane.showMessageDialog(view, "User added successfully.");
            loadUserTable();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(view, "Error adding user.");
        }
    }

    private void updateUser() {
        String id = view.txtUserId.getText().trim();
        String name = view.txtName.getText().trim();
        String email = view.txtEmail.getText().trim();
        String password = view.txtPassword.getText().trim();
        String role = (String) view.cmbRole.getSelectedItem();

        if (UserValidator.isEmpty(id, name, email, password)) {
            JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            return;
        }

        if (!UserValidator.isValidEmail(email)) {
            JOptionPane.showMessageDialog(view, "Invalid email format.");
            return;
        }

        if (!UserValidator.isValidPassword(password)) {
            JOptionPane.showMessageDialog(view, "Password must be at least 6 characters.");
            return;
        }

        User user = new User(id, name, email, password, role);
        if (userDAO.update(user)) {
            JOptionPane.showMessageDialog(view, "User updated successfully.");
            loadUserTable();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(view, "Error updating user.");
        }
    }

    private void deleteUser() {
        String id = view.txtUserId.getText().trim();

        if (UserValidator.isEmpty(id)) {
            JOptionPane.showMessageDialog(view, "Please enter the User ID to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this user?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (userDAO.delete(id)) {
                JOptionPane.showMessageDialog(view, "User deleted successfully.");
                loadUserTable();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(view, "Error deleting user.");
            }
        }
    }

    private void showUser() {
        String userId = view.txtUserId.getText().trim();

        if (UserValidator.isEmpty(userId)) {
            loadUserTable();
            return;
        }

        User user = userDAO.getById(userId);
        if (user != null) {
            Object[][] data = {
                UserHelper.toTableRow(user)
            };
            String[] columnNames = {"User ID", "Name", "Email", "Password", "Role"};
            view.updateUserTable(data, columnNames);
        } else {
            JOptionPane.showMessageDialog(view, "User not found.");
            loadUserTable();
        }
    }

    private void loadUserTable() {
        List<User> users = userDAO.getAll();
        String[] columnNames = {"User ID", "Name", "Email", "Password", "Role"};
        Object[][] data = new Object[users.size()][5];

        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            data[i][0] = u.getUserId();
            data[i][1] = u.getName();
            data[i][2] = u.getEmail();
            data[i][3] = u.getPassword();
            data[i][4] = u.getRole();
        }

        view.updateUserTable(data, columnNames);
    }

    private void fillFormFromTable(ListSelectionEvent e) {
        int selectedRow = view.userTable.getSelectedRow();
        if (selectedRow >= 0) {
            view.txtUserId.setText(view.userTable.getValueAt(selectedRow, 0).toString());
            view.txtName.setText(view.userTable.getValueAt(selectedRow, 1).toString());
            view.txtEmail.setText(view.userTable.getValueAt(selectedRow, 2).toString());
            view.txtPassword.setText(view.userTable.getValueAt(selectedRow, 3).toString());
            view.cmbRole.setSelectedItem(view.userTable.getValueAt(selectedRow, 4).toString());
        }
    }

    private void clearForm() {
        view.txtUserId.setText("");
        view.txtName.setText("");
        view.txtEmail.setText("");
        view.txtPassword.setText("");
        view.cmbRole.setSelectedIndex(0);
        view.userTable.clearSelection();
    }
}
