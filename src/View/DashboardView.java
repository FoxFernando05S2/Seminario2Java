package view;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JPanel {

    public JLabel lblTotalClients = new JLabel("Clients: 0");
    public JLabel lblTotalEmployees = new JLabel("Employees: 0");
    public JLabel lblTotalSales = new JLabel("Sales: 0");
    public JLabel lblTotalServices = new JLabel("Services: 0");

    public DashboardView() {
        setLayout(new GridLayout(2, 2, 20, 20));
        setBackground(Color.decode("#F4F7FA"));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(createCard(lblTotalClients, "#42A5F5"));
        add(createCard(lblTotalEmployees, "#66BB6A"));
        add(createCard(lblTotalSales, "#FFA726"));
        add(createCard(lblTotalServices, "#AB47BC"));
    }

    private JPanel createCard(JLabel label, String colorHex) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.decode(colorHex));
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }
}
