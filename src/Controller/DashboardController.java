package controller;

import Model.Dao.ClientDAO;
import Model.Dao.EmployeeDAO;
import Model.Dao.PaymentDAO;
import Model.Dao.ServiceDAO;
import view.DashboardView;

public class DashboardController {

    private DashboardView view;

    public DashboardController(DashboardView view) {
        this.view = view;
        loadStatistics();
    }

    public void loadStatistics() {
        // DAO para obtener los datos reales
        ClientDAO clientDAO = new ClientDAO();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        ServiceDAO serviceDAO = new ServiceDAO();
        PaymentDAO paymentDAO = new PaymentDAO(); // Considerando ventas como pagos

        int totalClients = clientDAO.getAll().size();
        int totalEmployees = employeeDAO.getAll().size();
        int totalServices = serviceDAO.getAll().size();
        int totalSales = paymentDAO.getAll().size(); // Puedes cambiar esto si las ventas están en otra tabla

        // Actualizar vista
        view.lblTotalClients.setText("Clients: " + totalClients);
        view.lblTotalEmployees.setText("Employees: " + totalEmployees);
        view.lblTotalSales.setText("Sales: " + totalSales);
        view.lblTotalServices.setText("Services: " + totalServices);
    }
}
