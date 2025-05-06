package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    public JTextField txtEmail = new JTextField(18);
    public JPasswordField txtPassword = new JPasswordField(18);
    public JButton btnLogin = new JButton("Login");

    public LoginView() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 330);
        setLocationRelativeTo(null);
        setResizable(false);

        // Colores y fuentes
        Color backgroundColor = Color.decode("#E3F2FD");      // Fondo principal claro
        Color borderColor = Color.decode("#1565C0");          // Azul intenso para bordes y títulos
        Color buttonColor = Color.decode("#1976D2");          // Botón azul fuerte
        Color buttonHoverColor = Color.decode("#0D47A1");     // Botón hover azul más intenso
        Color fontColor = Color.decode("#222");               // Texto principal
        Color fieldBorderColor = borderColor;                 // Borde campos
        Color fieldBgColor = Color.WHITE;                     // Fondo campos
        Font font = new Font("Segoe UI", Font.PLAIN, 15);
        Font fontBold = new Font("Segoe UI", Font.BOLD, 26);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(backgroundColor);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        // Título
        JLabel titleLabel = new JLabel("Welcome Back", SwingConstants.CENTER);
        titleLabel.setFont(fontBold);
        titleLabel.setForeground(borderColor);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Panel formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(backgroundColor);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor, 2, true),
                BorderFactory.createEmptyBorder(18, 24, 18, 24)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.anchor = GridBagConstraints.WEST;

        // Email Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.NONE;
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(font);
        lblEmail.setForeground(fontColor);
        formPanel.add(lblEmail, gbc);

        // Email TextField con recuadro y ancho aumentado
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtEmail.setFont(font);
        txtEmail.setBackground(fieldBgColor);
        txtEmail.setForeground(fontColor);
        txtEmail.setCaretColor(borderColor);
        txtEmail.setBorder(BorderFactory.createLineBorder(fieldBorderColor, 2, true));
        txtEmail.setPreferredSize(new Dimension(320, 28));  // Ancho aumentado, altura estándar
        formPanel.add(txtEmail, gbc);

        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.NONE;
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(font);
        lblPassword.setForeground(fontColor);
        formPanel.add(lblPassword, gbc);

        // Password Field con recuadro y ancho aumentado
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtPassword.setFont(font);
        txtPassword.setBackground(fieldBgColor);
        txtPassword.setForeground(fontColor);
        txtPassword.setCaretColor(borderColor);
        txtPassword.setBorder(BorderFactory.createLineBorder(fieldBorderColor, 2, true));
        txtPassword.setPreferredSize(new Dimension(320, 28));  // Ancho aumentado, altura estándar
        formPanel.add(txtPassword, gbc);

        mainPanel.add(formPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Panel botón
        btnLogin.setBackground(buttonColor);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(buttonHoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(buttonColor);
            }
        });

        mainPanel.add(btnLogin);

        add(mainPanel);
    }

    // Método para agregar listener al botón login
    public void addLoginListener(ActionListener actionListener) {
        btnLogin.addActionListener(actionListener);
    }

    // Método para mostrar mensaje de error
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Método para mostrar mensaje informativo
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
