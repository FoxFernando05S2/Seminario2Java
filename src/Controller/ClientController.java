package controller;

import Helper.ClientHelper;
import Model.Entities.Client;
import Model.Dao.ClientDAO;
import Model.validation.ClientValidator;
import view.ClientView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.util.List;

public class ClientController {

    private ClientView view;
    private ClientDAO clientDAO;

    public ClientController(ClientView view, ClientDAO clientDAO) {
        this.view = view;
        this.clientDAO = clientDAO;

        loadClientTable();
        addActionListeners();
    }

    private void addActionListeners() {
        view.btnAdd.addActionListener(e -> addClient());
        view.btnUpdate.addActionListener(e -> updateClient());
        view.btnDelete.addActionListener(e -> deleteClient());
        view.btnShow.addActionListener(e -> showClient());

        view.clientTable.getSelectionModel().addListSelectionListener(this::fillFormFromTable);
    }

    private void addClient() {
        Client client = ClientHelper.fromForm(view);

        String error = ClientValidator.getValidationError(client);
        if (!error.isEmpty()) {
            JOptionPane.showMessageDialog(view, error);
            return;
        }

        if (clientDAO.insert(client)) {
            JOptionPane.showMessageDialog(view, "Client added successfully.");
            loadClientTable();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(view, "Error adding client.");
        }
    }

    private void updateClient() {
        Client client = ClientHelper.fromForm(view);

        String error = ClientValidator.getValidationError(client);
        if (!error.isEmpty()) {
            JOptionPane.showMessageDialog(view, error);
            return;
        }

        if (clientDAO.getById(client.getClientId()) == null) {
            JOptionPane.showMessageDialog(view, "Client ID does not exist.");
            return;
        }

        if (clientDAO.update(client)) {
            JOptionPane.showMessageDialog(view, "Client updated successfully.");
            loadClientTable();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(view, "Error updating client.");
        }
    }

    private void deleteClient() {
        String clientId = view.txtClientId.getText().trim();
        if (clientId.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter the Client ID to delete.");
            return;
        }

        if (clientDAO.getById(clientId) == null) {
            JOptionPane.showMessageDialog(view, "Client ID does not exist.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this client?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION && clientDAO.delete(clientId)) {
            JOptionPane.showMessageDialog(view, "Client deleted successfully.");
            loadClientTable();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(view, "Error deleting client.");
        }
    }

    private void showClient() {
        String clientId = view.txtClientId.getText().trim();
        if (clientId.isEmpty()) {
            loadClientTable();
            return;
        }

        Client client = clientDAO.getById(clientId);
        if (client != null) {
            Object[][] data = {
                {client.getClientId(), client.getUserId(), client.getCompanyName(), client.getContactNumber()}
            };
            String[] columnNames = {"Client ID", "User ID", "Company Name", "Contact Number"};
            view.updateClientTable(data, columnNames);
        } else {
            JOptionPane.showMessageDialog(view, "Client not found.");
            loadClientTable();
        }
    }

    private void loadClientTable() {
        List<Client> clients = clientDAO.getAll();
        Object[][] data = clients.stream()
                .map(c -> new Object[]{c.getClientId(), c.getUserId(), c.getCompanyName(), c.getContactNumber()})
                .toArray(Object[][]::new);

        String[] columnNames = {"Client ID", "User ID", "Company Name", "Contact Number"};
        view.updateClientTable(data, columnNames);
    }

    private void fillFormFromTable(ListSelectionEvent e) {
        int selectedRow = view.clientTable.getSelectedRow();
        if (selectedRow >= 0) {
            view.txtClientId.setText(view.clientTable.getValueAt(selectedRow, 0).toString());
            view.txtUserId.setText(view.clientTable.getValueAt(selectedRow, 1).toString());
            view.txtCompanyName.setText(view.clientTable.getValueAt(selectedRow, 2).toString());
            view.txtContactNumber.setText(view.clientTable.getValueAt(selectedRow, 3).toString());
        }
    }

    private void clearForm() {
        view.txtClientId.setText("");
        view.txtUserId.setText("");
        view.txtCompanyName.setText("");
        view.txtContactNumber.setText("");
        view.clientTable.clearSelection();
    }
}
