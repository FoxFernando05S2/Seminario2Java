package App;

import Controller.LoginController;
import Model.LoginDAO;
import view.LoginView;

public class Main {
    public static void main(String[] args) {
        // Crea la vista de login
        LoginView view = new LoginView();
        
        // Crea el DAO para la base de datos de login
        LoginDAO dao = new LoginDAO();
        
        // Crea el controlador de login
        new LoginController(view, dao);
        
        // Muestra la ventana de login
        view.setVisible(true);
    }
}
