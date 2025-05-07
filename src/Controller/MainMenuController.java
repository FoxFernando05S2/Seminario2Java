package controller;

import Model.Dao.ClientDAO;
import Model.Dao.EmployeeDAO;
import Model.Dao.PaymentDAO;
import Model.Dao.ServiceDAO;
import Model.Dao.UserDAO;
import view.ClientView;
import view.DashboardView;
import view.EmployeeView;
import view.MainMenuView;
import view.PaymentView;
import view.ServiceView;
import view.UserView;

public class MainMenuController {

    private MainMenuView view;
    private UserDAO userDAO;

    public MainMenuController(MainMenuView view, UserDAO userDAO) {
        this.view = view;
        this.userDAO = userDAO;
        
        view.addDashboardListener(e -> {
            DashboardView dashboardView = new DashboardView();
            view.getMainPanel().removeAll();
            view.getMainPanel().add(dashboardView);
            view.getMainPanel().revalidate();
            view.getMainPanel().repaint();
            new DashboardController(dashboardView);
        });
        
        // Usuarios
        view.addGestionUsuariosListener(e -> {
            UserView userView = new UserView();
            view.getMainPanel().removeAll();
            view.getMainPanel().add(userView);
            view.getMainPanel().revalidate();
            view.getMainPanel().repaint();
            new UserController(userView, userDAO);
        });

        // Clientes
        view.addGestionClientesListener(e -> {
            ClientView clientView = new ClientView();
            view.getMainPanel().removeAll();
            view.getMainPanel().add(clientView);
            view.getMainPanel().revalidate();
            view.getMainPanel().repaint();
            new ClientController(clientView, new ClientDAO());
        });

        // Servicios
        view.addGestionServicioListener(e -> {
            ServiceView serviceView = new ServiceView();
            view.getMainPanel().removeAll();
            view.getMainPanel().add(serviceView);
            view.getMainPanel().revalidate();
            view.getMainPanel().repaint();
            new ServiceController(serviceView, new ServiceDAO());
        });

        // Empleados
        view.addGestionEmployeeListener(e -> {
            EmployeeView employeeView = new EmployeeView();
            view.getMainPanel().removeAll();
            view.getMainPanel().add(employeeView);
            view.getMainPanel().revalidate();
            view.getMainPanel().repaint();
            new EmployeeController(employeeView, new EmployeeDAO());
        });

        // Pagos
        view.addGestionPaymentListener(e -> {
            PaymentView paymentView = new PaymentView();
            view.getMainPanel().removeAll();
            view.getMainPanel().add(paymentView);
            view.getMainPanel().revalidate();
            view.getMainPanel().repaint();
            new PaymentController(paymentView, new PaymentDAO());
        });

        
    }
}
