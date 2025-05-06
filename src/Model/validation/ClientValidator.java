/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.validation;

import Model.Entities.Client;

/**
 *
 * @author Usuario
 */
public class ClientValidator {

    public static boolean isValid(Client client) {
        return getValidationError(client).isEmpty();
    }

    public static String getValidationError(Client client) {
        if (client == null) {
            return "Client cannot be null.";
        }

        if (client.getClientId() == null || client.getClientId().trim().isEmpty()) {
            return "Client ID is required.";
        }

        if (client.getUserId() == null || client.getUserId().trim().isEmpty()) {
            return "User ID is required.";
        }

        if (client.getCompanyName() == null || client.getCompanyName().trim().isEmpty()) {
            return "Company name is required.";
        }

        if (client.getContactNumber() == null || client.getContactNumber().trim().isEmpty()) {
            return "Contact number is required.";
        }

        if (!client.getContactNumber().matches("\\d{7,15}")) {
            return "Contact number must be 7 to 15 digits long.";
        }

        return "";
    }
}
