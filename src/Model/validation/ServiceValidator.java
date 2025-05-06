/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.validation;

import Model.Entities.Service;

/**
 *
 * @author Usuario
 */
public class ServiceValidator {

    public static boolean isValid(Service service) {
        return getValidationError(service).isEmpty();
    }

    public static String getValidationError(Service service) {
        if (service == null) {
            return "El servicio no puede ser null.";
        }

        if (service.getServiceId() == null || service.getServiceId().trim().isEmpty()) {
            return "El ID del servicio es obligatorio.";
        }

        if (service.getName() == null || service.getName().trim().isEmpty()) {
            return "El nombre del servicio es obligatorio.";
        }

        if (service.getPrice() < 0) {
            return "El precio del servicio no puede ser negativo.";
        }

        return "";
    }
}
