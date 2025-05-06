package controller;

import Helper.PaymentHelper;
import Model.Entities.Payment;
import Model.Dao.PaymentDAO;
import Model.validation.PaymentValidator;
import view.PaymentView;

import java.sql.Date;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class PaymentController {

    private PaymentView view;
    private PaymentDAO paymentDAO;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public PaymentController(PaymentView view, PaymentDAO paymentDAO) {
        this.view = view;
        this.paymentDAO = paymentDAO;

        loadPaymentTable();
        addActionListeners();
    }

    private void addActionListeners() {
        view.btnAdd.addActionListener(e -> addPayment());
        view.btnUpdate.addActionListener(e -> updatePayment());
        view.btnDelete.addActionListener(e -> deletePayment());
        view.btnShow.addActionListener(e -> showPayment());

        view.paymentTable.getSelectionModel().addListSelectionListener(this::fillFormFromTable);
    }

    private void addPayment() {
        String paymentId = view.txtPaymentId.getText().trim();
        String clientId = view.txtClientId.getText().trim();
        String amountStr = view.txtAmount.getText().trim();
        String dateStr = view.txtPaymentDate.getText().trim();
        String status = (String) view.cmbStatus.getSelectedItem();

        if (PaymentValidator.isEmpty(paymentId, clientId, amountStr, dateStr)) {
            JOptionPane.showMessageDialog(view, "Please fill in all required fields.");
            return;
        }

        if (!PaymentValidator.isValidAmount(amountStr)) {
            JOptionPane.showMessageDialog(view, "Amount must be a valid number.");
            return;
        }

        if (!PaymentValidator.isValidDate(dateStr)) {
            JOptionPane.showMessageDialog(view, "Payment date must be in format yyyy-MM-dd.");
            return;
        }

        if (paymentDAO.getById(paymentId) != null) {
            JOptionPane.showMessageDialog(view, "Payment ID already exists.");
            return;
        }

        try {
            double amount = PaymentHelper.parseAmount(amountStr);

            // Suponemos que parseSqlDate devuelve java.util.Date
            java.util.Date utilDate = PaymentHelper.parseSqlDate(dateStr);
            java.sql.Date paymentDate = new java.sql.Date(utilDate.getTime());

            Payment payment = new Payment(paymentId, clientId, amount, paymentDate, status);

            if (paymentDAO.insert(payment)) {
                JOptionPane.showMessageDialog(view, "Payment added successfully.");
                loadPaymentTable();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(view, "Error adding payment.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Invalid data: " + e.getMessage());
        }
    }

    private void updatePayment() {
        String paymentId = view.txtPaymentId.getText().trim();
        String clientId = view.txtClientId.getText().trim();
        String amountStr = view.txtAmount.getText().trim();
        String dateStr = view.txtPaymentDate.getText().trim();
        String status = (String) view.cmbStatus.getSelectedItem();

        if (PaymentValidator.isEmpty(paymentId, clientId, amountStr, dateStr)) {
            JOptionPane.showMessageDialog(view, "Please fill in all required fields.");
            return;
        }

        if (!PaymentValidator.isValidAmount(amountStr)) {
            JOptionPane.showMessageDialog(view, "Amount must be a valid number.");
            return;
        }

        if (!PaymentValidator.isValidDate(dateStr)) {
            JOptionPane.showMessageDialog(view, "Payment date must be in format yyyy-MM-dd.");
            return;
        }

        if (paymentDAO.getById(paymentId) == null) {
            JOptionPane.showMessageDialog(view, "Payment ID does not exist.");
            return;
        }

        try {
            double amount = PaymentHelper.parseAmount(amountStr);
            
            java.util.Date utilDate = PaymentHelper.parseSqlDate(dateStr);
            java.sql.Date paymentDate = new java.sql.Date(utilDate.getTime());
            
            Payment payment = new Payment(paymentId, clientId, amount, paymentDate, status);

            if (paymentDAO.update(payment)) {
                JOptionPane.showMessageDialog(view, "Payment updated successfully.");
                loadPaymentTable();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(view, "Error updating payment.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Invalid data: " + e.getMessage());
        }
    }

    private void deletePayment() {
        String paymentId = view.txtPaymentId.getText().trim();

        if (PaymentValidator.isEmpty(paymentId)) {
            JOptionPane.showMessageDialog(view, "Please enter the Payment ID to delete.");
            return;
        }

        if (paymentDAO.getById(paymentId) == null) {
            JOptionPane.showMessageDialog(view, "Payment ID does not exist.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this payment?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (paymentDAO.delete(paymentId)) {
                JOptionPane.showMessageDialog(view, "Payment deleted successfully.");
                loadPaymentTable();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(view, "Error deleting payment.");
            }
        }
    }

    private void showPayment() {
        String paymentId = view.txtPaymentId.getText().trim();

        if (PaymentValidator.isEmpty(paymentId)) {
            loadPaymentTable();
            return;
        }

        Payment payment = paymentDAO.getById(paymentId);
        if (payment != null) {
            Object[][] data = {
                PaymentHelper.toTableRow(payment)
            };
            String[] columnNames = {"Payment ID", "Client ID", "Amount", "Payment Date", "Status"};
            view.updatePaymentTable(data, columnNames);
        } else {
            JOptionPane.showMessageDialog(view, "Payment not found.");
            loadPaymentTable();
        }
    }

    private void loadPaymentTable() {
        List<Payment> payments = paymentDAO.getAll();
        String[] columnNames = {"Payment ID", "Client ID", "Amount", "Payment Date", "Status"};
        Object[][] data = new Object[payments.size()][5];

        for (int i = 0; i < payments.size(); i++) {
            Payment p = payments.get(i);
            data[i][0] = p.getPaymentId();
            data[i][1] = p.getClientId();
            data[i][2] = p.getAmount();
            data[i][3] = dateFormat.format(p.getPaymentDate());
            data[i][4] = p.getStatus();
        }

        view.updatePaymentTable(data, columnNames);
    }

    private void fillFormFromTable(ListSelectionEvent e) {
        int selectedRow = view.paymentTable.getSelectedRow();
        if (selectedRow >= 0) {
            view.txtPaymentId.setText(view.paymentTable.getValueAt(selectedRow, 0).toString());
            view.txtClientId.setText(view.paymentTable.getValueAt(selectedRow, 1).toString());
            view.txtAmount.setText(view.paymentTable.getValueAt(selectedRow, 2).toString());
            view.txtPaymentDate.setText(view.paymentTable.getValueAt(selectedRow, 3).toString());
            view.cmbStatus.setSelectedItem(view.paymentTable.getValueAt(selectedRow, 4).toString());
        }
    }

    private void clearForm() {
        view.txtPaymentId.setText("");
        view.txtClientId.setText("");
        view.txtAmount.setText("");
        view.txtPaymentDate.setText("");
        view.cmbStatus.setSelectedIndex(0);
        view.paymentTable.clearSelection();
    }
}
