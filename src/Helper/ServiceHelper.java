/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import Model.Entities.Service;
import view.ServiceView;

/**
 *
 * @author Usuario
 */
public class ServiceHelper {

    public static Service getServiceFromForm(ServiceView view) {
        String id = view.txtServiceId.getText().trim();
        String name = view.txtName.getText().trim();
        String description = view.txtDescription.getText().trim();
        String priceStr = view.txtPrice.getText().trim();

        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            price = -1; // se valida luego
        }

        return new Service(id, name, description, price);
    }

    public static Object[] toTableRow(Service service) {
        return new Object[]{
            service.getServiceId(),
            service.getName(),
            service.getDescription(),
            service.getPrice()
        };
    }

}
