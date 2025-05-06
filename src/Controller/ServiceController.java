package controller;

import Helper.ServiceHelper;
import Model.Entities.Service;
import Model.Dao.ServiceDAO;
import Model.validation.ServiceValidator;
import view.ServiceView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.util.List;

public class ServiceController {

    private final ServiceView view;
    private final ServiceDAO serviceDAO;

    public ServiceController(ServiceView view, ServiceDAO serviceDAO) {
        this.view = view;
        this.serviceDAO = serviceDAO;
        loadServiceTable();
        addActionListeners();
    }

    private void addActionListeners() {
        view.btnAdd.addActionListener(e -> addService());
        view.btnUpdate.addActionListener(e -> updateService());
        view.btnDelete.addActionListener(e -> deleteService());
        view.btnShow.addActionListener(e -> showService());
        view.serviceTable.getSelectionModel().addListSelectionListener(this::fillFormFromTable);
    }

    private void addService() {
        Service service = ServiceHelper.getServiceFromForm(view);

        String error = ServiceValidator.getValidationError(service);
        if (!error.isEmpty()) {
            JOptionPane.showMessageDialog(view, error);
            return;
        }

        if (serviceDAO.getById(service.getServiceId()) != null) {
            JOptionPane.showMessageDialog(view, "El ID del servicio ya existe.");
            return;
        }

        if (serviceDAO.insert(service)) {
            JOptionPane.showMessageDialog(view, "Servicio agregado exitosamente.");
            loadServiceTable();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(view, "Error al agregar el servicio.");
        }
    }

    private void updateService() {
        Service service = ServiceHelper.getServiceFromForm(view);

        String error = ServiceValidator.getValidationError(service);
        if (!error.isEmpty()) {
            JOptionPane.showMessageDialog(view, error);
            return;
        }

        if (serviceDAO.getById(service.getServiceId()) == null) {
            JOptionPane.showMessageDialog(view, "El ID del servicio no existe.");
            return;
        }

        if (serviceDAO.update(service)) {
            JOptionPane.showMessageDialog(view, "Servicio actualizado correctamente.");
            loadServiceTable();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(view, "Error al actualizar el servicio.");
        }
    }

    private void deleteService() {
        String serviceId = view.txtServiceId.getText().trim();
        if (serviceId.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Por favor ingrese el ID del servicio a eliminar.");
            return;
        }

        if (serviceDAO.getById(serviceId) == null) {
            JOptionPane.showMessageDialog(view, "El ID del servicio no existe.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view, "¿Está seguro que desea eliminar este servicio?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (serviceDAO.delete(serviceId)) {
                JOptionPane.showMessageDialog(view, "Servicio eliminado exitosamente.");
                loadServiceTable();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(view, "Error al eliminar el servicio.");
            }
        }
    }

    private void showService() {
        String serviceId = view.txtServiceId.getText().trim();
        if (serviceId.isEmpty()) {
            loadServiceTable();
            return;
        }

        Service service = serviceDAO.getById(serviceId);
        if (service != null) {
            String[] columns = {"Service ID", "Name", "Description", "Price"};
            Object[][] data = {ServiceHelper.toTableRow(service)};
            view.updateServiceTable(data, columns);
        } else {
            JOptionPane.showMessageDialog(view, "Servicio no encontrado.");
            loadServiceTable();
        }
    }

    private void loadServiceTable() {
        List<Service> services = serviceDAO.getAll();
        String[] columns = {"Service ID", "Name", "Description", "Price"};
        Object[][] data = services.stream()
                .map(ServiceHelper::toTableRow)
                .toArray(Object[][]::new);
        view.updateServiceTable(data, columns);
    }

    private void fillFormFromTable(ListSelectionEvent e) {
        int row = view.serviceTable.getSelectedRow();
        if (row >= 0) {
            view.txtServiceId.setText(view.serviceTable.getValueAt(row, 0).toString());
            view.txtName.setText(view.serviceTable.getValueAt(row, 1).toString());
            view.txtDescription.setText(view.serviceTable.getValueAt(row, 2).toString());
            view.txtPrice.setText(view.serviceTable.getValueAt(row, 3).toString());
        }
    }

    private void clearForm() {
        view.txtServiceId.setText("");
        view.txtName.setText("");
        view.txtDescription.setText("");
        view.txtPrice.setText("");
        view.serviceTable.clearSelection();
    }
}
