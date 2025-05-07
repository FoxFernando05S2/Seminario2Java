package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import controller.DashboardController;


public class MainMenuView extends JFrame {
    private String userName;
    private String userRole;

    private JMenuItem userItem;
    private JMenuItem clientItem;
    private JMenuItem serviceItem;
    private JMenuItem employeeItem;
    private JMenuItem paymentItem;
    private JMenuItem dashboardItem;

    private JPanel mainPanel;

    public MainMenuView(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;

        setTitle("Main Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menú
        JMenuBar menuBar = new JMenuBar();
        JMenu gestionMenu = new JMenu("Management");

        userItem = new JMenuItem("Manage Users");
        clientItem = new JMenuItem("Manage Clients");
        serviceItem = new JMenuItem("Manage Services");
        employeeItem = new JMenuItem("Manage Employees");
        paymentItem = new JMenuItem("Manage Payments");
        dashboardItem = new JMenuItem("Dashboard");

        gestionMenu.add(userItem);
        gestionMenu.add(clientItem);
        gestionMenu.add(serviceItem);
        gestionMenu.add(employeeItem);
        gestionMenu.add(paymentItem);
        gestionMenu.addSeparator();
        gestionMenu.add(dashboardItem);

        menuBar.add(gestionMenu);
        setJMenuBar(menuBar);

        JLabel userLabel = new JLabel("Welcome, " + userName + " - Role: " + userRole, SwingConstants.CENTER);
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        userLabel.setForeground(Color.decode("#1565C0"));
        add(userLabel, BorderLayout.NORTH);

        // Panel principal con imagen de fondo
        mainPanel = new ImagePanel("/resources/TISE.jpg");
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);
    }

    // Panel con imagen de fondo
    public static class ImagePanel extends JPanel {
        private Image backgroundImage;

        public ImagePanel(String imagePath) {
            backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    // ✅ Este método debe ser público para que el controlador pueda acceder
    public JPanel getMainPanel() {
        return mainPanel;
    }

    // Métodos para asignar listeners
    public void addGestionUsuariosListener(ActionListener listener) {
        userItem.addActionListener(listener);
    }

    public void addGestionClientesListener(ActionListener listener) {
        clientItem.addActionListener(listener);
    }

    public void addGestionServicioListener(ActionListener listener) {
        serviceItem.addActionListener(listener);
    }

    public void addGestionEmployeeListener(ActionListener listener) {
        employeeItem.addActionListener(listener);
    }

    public void addGestionPaymentListener(ActionListener listener) {
        paymentItem.addActionListener(listener);
    }

    public void addDashboardListener(ActionListener listener) {
        dashboardItem.addActionListener(listener);
    }
}