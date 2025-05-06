package view;

import javax.swing.*;
import java.awt.*;

public class ServiceView extends JPanel {
    public JTextField txtServiceId = new JTextField(10);
    public JTextField txtName = new JTextField(20);
    public JTextArea txtDescription = new JTextArea(3, 20);
    public JTextField txtPrice = new JTextField(10);

    public JButton btnAdd = new JButton("Add");
    public JButton btnUpdate = new JButton("Update");
    public JButton btnDelete = new JButton("Delete");
    public JButton btnShow = new JButton("Show");

    public JTable serviceTable = new JTable();
    private JScrollPane scrollPane = new JScrollPane(serviceTable);

    public ServiceView() {
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

        // Panel formulario con GridBagLayout
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(backgroundColor);
        panelForm.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(borderColor, 2), "Service Data", 0, 0, fontBold, borderColor));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Service ID
        gbc.gridx = 0; gbc.gridy = 0;
        panelForm.add(styledLabel("Service ID:", fontBold, fontColor), gbc);
        gbc.gridx = 1;
        txtServiceId.setFont(font);
        panelForm.add(txtServiceId, gbc);

        // Name
        gbc.gridx = 0; gbc.gridy = 1;
        panelForm.add(styledLabel("Name:", fontBold, fontColor), gbc);
        gbc.gridx = 1;
        txtName.setFont(font);
        panelForm.add(txtName, gbc);

        // Description
        gbc.gridx = 0; gbc.gridy = 2;
        panelForm.add(styledLabel("Description:", fontBold, fontColor), gbc);
        gbc.gridx = 1;
        JScrollPane descScroll = new JScrollPane(txtDescription);
        txtDescription.setFont(font);
        panelForm.add(descScroll, gbc);

        // Price
        gbc.gridx = 0; gbc.gridy = 3;
        panelForm.add(styledLabel("Price:", fontBold, fontColor), gbc);
        gbc.gridx = 1;
        txtPrice.setFont(font);
        panelForm.add(txtPrice, gbc);

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
            BorderFactory.createLineBorder(borderColor, 2), "Service List", 0, 0, fontBold, borderColor));
        serviceTable.setFont(font);
        serviceTable.setRowHeight(28);
        serviceTable.getTableHeader().setFont(fontBold);
        serviceTable.getTableHeader().setBackground(borderColor);
        serviceTable.getTableHeader().setForeground(Color.WHITE);
        serviceTable.setSelectionBackground(buttonColor);
        serviceTable.setSelectionForeground(Color.WHITE);

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

    public void updateServiceTable(Object[][] data, String[] columnNames) {
        serviceTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}
