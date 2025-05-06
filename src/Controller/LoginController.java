package Controller;

import Model.Login;
import Model.LoginDAO;
import view.LoginView;
import view.MainMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginController {
    private LoginView view;
    private LoginDAO userDAO;

    public LoginController(LoginView view, LoginDAO userDAO) {
        this.view = view;
        this.userDAO = userDAO;

        this.view.btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = view.txtEmail.getText();
                String password = new String(view.txtPassword.getPassword());

                // Validar credenciales
                Login user = userDAO.login(email, password);
                if (user != null) {
                    // Validar rol permitido
                    if (user.getRole().equalsIgnoreCase("admin") || user.getRole().equalsIgnoreCase("employee")) {
                        view.showMessage("Welcome, " + user.getName() + " (" + user.getRole() + ")");
                        view.dispose();

                        // Crear y mostrar la ventana de menú principal
                        MainMenuView menu = new MainMenuView(user.getName(), user.getRole());
                        new controller.MainMenuController(menu, new Model.Dao.UserDAO());
                        menu.setVisible(true);
                    } else {
                        // Rol no permitido (cliente u otro)
                        view.showMessage("Acceso denegado: no tiene permisos para ingresar.");
                    }
                } else {
                    view.showMessage("Invalid credentials");
                }
            }
        });
    }
}
