package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuView extends JFrame {
    private String userName;
    private String userRole;

    private JMenuItem userItem;
    private JMenuItem clientItem;
    private JMenuItem serviceItem;
    private JMenuItem employeeItem;
    private JMenuItem paymentItem;

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

        gestionMenu.add(userItem);
        gestionMenu.add(clientItem);
        gestionMenu.add(serviceItem);
        gestionMenu.add(employeeItem);
        gestionMenu.add(paymentItem);
        menuBar.add(gestionMenu);
        setJMenuBar(menuBar);

        JLabel userLabel = new JLabel("Welcome, " + userName + " - Role: " + userRole, SwingConstants.CENTER);
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        userLabel.setForeground(Color.decode("#1565C0"));
        add(userLabel, BorderLayout.NORTH);

        // Usar ImagePanel para mostrar imagen de fondo
        mainPanel = new ImagePanel("/resources/TISE.jpg");
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);
    }

    // Clase interna para panel con imagen de fondo
    public static class ImagePanel extends JPanel {
        private Image backgroundImage;

        public ImagePanel(String imagePath) {
            backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                // Dibuja la imagen escalada al tamaño del panel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

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
}
