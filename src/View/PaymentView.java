package view;

import javax.swing.*;
import java.awt.*;

public class PaymentView extends JPanel {
    public JTextField txtPaymentId = new JTextField(10);
    public JTextField txtClientId = new JTextField(10);
    public JTextField txtAmount = new JTextField(10);
    public JTextField txtPaymentDate = new JTextField(10); // Formato: yyyy-MM-dd
    public JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"Pending", "Paid"});

    public JButton btnAdd = new JButton("Add");
    public JButton btnUpdate = new JButton("Update");
    public JButton btnDelete = new JButton("Delete");
    public JButton btnShow = new JButton("Show");

    public JTable paymentTable = new JTable();
    private JScrollPane scrollPane = new JScrollPane(paymentTable);

    public PaymentView() {
        // Paleta de colores profesional y clara
        Color backgroundColor = Color.decode("#F4F7FA");    // Fondo principal
        Color borderColor = Color.decode("#2196F3");        // Bordes y títulos (celeste fuerte)
        Color buttonColor = Color.decode("#1976D2");        // Botón principal
        Color buttonHoverColor = Color.decode("#1565C0");   // Botón hover
        Color fontColor = Color.decode("#222");             // Texto principal
        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontBold = new Font("Segoe UI", Font.BOLD, 16);

        setLayout(new BorderLayout(10, 10));
        setBackground(backgroundColor);

        // Panel formulario
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));
        panelForm.setBackground(backgroundColor);
        panelForm.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(borderColor, 2), "Payment Data", 0, 0, fontBold, borderColor));

        panelForm.add(styledLabel("Payment ID:", fontBold, fontColor));
        txtPaymentId.setFont(font);
        panelForm.add(txtPaymentId);

        panelForm.add(styledLabel("Client ID:", fontBold, fontColor));
        txtClientId.setFont(font);
        panelForm.add(txtClientId);

        panelForm.add(styledLabel("Amount:", fontBold, fontColor));
        txtAmount.setFont(font);
        panelForm.add(txtAmount);

        panelForm.add(styledLabel("Payment Date (yyyy-MM-dd):", fontBold, fontColor));
        txtPaymentDate.setFont(font);
        panelForm.add(txtPaymentDate);

        panelForm.add(styledLabel("Status:", fontBold, fontColor));
        cmbStatus.setFont(font);
        panelForm.add(cmbStatus);

        // Panel botones
        JPanel panelButtons = new JPanel(new GridLayout(1, 4, 15, 15));
        panelButtons.setBackground(backgroundColor);
        customizeButton(btnAdd, buttonColor, buttonHoverColor, fontBold, Color.WHITE);
        customizeButton(btnUpdate, buttonColor, buttonHoverColor, fontBold, Color.WHITE);
        customizeButton(btnDelete, buttonColor, buttonHoverColor, fontBold, Color.WHITE);
        customizeButton(btnShow, buttonColor, buttonHoverColor, fontBold, Color.WHITE);
        panelButtons.add(btnAdd);
        panelButtons.add(btnUpdate);
        panelButtons.add(btnDelete);
        panelButtons.add(btnShow);

        // Panel tabla
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(borderColor, 2), "Payment List", 0, 0, fontBold, borderColor));
        paymentTable.setFont(font);
        paymentTable.setRowHeight(28);
        paymentTable.getTableHeader().setFont(fontBold);
        paymentTable.getTableHeader().setBackground(borderColor);
        paymentTable.getTableHeader().setForeground(Color.WHITE);
        paymentTable.setSelectionBackground(buttonColor);
        paymentTable.setSelectionForeground(Color.WHITE);

        // Añadir componentes
        add(panelForm, BorderLayout.NORTH);
        add(panelButtons, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JLabel styledLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    private void customizeButton(JButton button, Color bgColor, Color hoverColor, Font font, Color fgColor) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(font);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    public void updatePaymentTable(Object[][] data, String[] columnNames) {
        paymentTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}
