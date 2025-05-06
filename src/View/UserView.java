package view;

import javax.swing.*;
import java.awt.*;

public class UserView extends JPanel {
    public JTextField txtUserId = new JTextField(10);
    public JTextField txtName = new JTextField(20);
    public JTextField txtEmail = new JTextField(20);
    public JTextField txtPassword = new JTextField(20);
    public JComboBox<String> cmbRole = new JComboBox<>(new String[]{"admin", "employee", "client"});

    public JButton btnAdd = new JButton("Add");
    public JButton btnUpdate = new JButton("Update");
    public JButton btnDelete = new JButton("Delete");
    public JButton btnShow = new JButton("Show");

    public JTable userTable = new JTable();
    private JScrollPane scrollPane = new JScrollPane(userTable);

    public UserView() {
        // Colores profesionales claros
        Color backgroundColor = Color.decode("#F4F7FA");    // Fondo principal
        Color borderColor = Color.decode("#2196F3");        // Bordes y títulos
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
            BorderFactory.createLineBorder(borderColor, 2), "User Data", 0, 0, fontBold, borderColor));

        // Etiquetas estilizadas
        panelForm.add(styledLabel("User ID:", fontBold, fontColor));
        txtUserId.setFont(font);
        panelForm.add(txtUserId);

        panelForm.add(styledLabel("Name:", fontBold, fontColor));
        txtName.setFont(font);
        panelForm.add(txtName);

        panelForm.add(styledLabel("Email:", fontBold, fontColor));
        txtEmail.setFont(font);
        panelForm.add(txtEmail);

        panelForm.add(styledLabel("Password:", fontBold, fontColor));
        txtPassword.setFont(font);
        panelForm.add(txtPassword);

        panelForm.add(styledLabel("Role:", fontBold, fontColor));
        cmbRole.setFont(font);
        panelForm.add(cmbRole);

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

        // Tabla con estilo
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(borderColor, 2), "User List", 0, 0, fontBold, borderColor));
        userTable.setFont(font);
        userTable.setRowHeight(28);
        userTable.getTableHeader().setFont(fontBold);
        userTable.getTableHeader().setBackground(borderColor);
        userTable.getTableHeader().setForeground(Color.WHITE);
        userTable.setSelectionBackground(buttonColor);
        userTable.setSelectionForeground(Color.WHITE);

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

    public void updateUserTable(Object[][] data, String[] columnNames) {
        userTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}
