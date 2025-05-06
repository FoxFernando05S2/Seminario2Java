/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import Model.Entities.Client;
import view.ClientView;

/**
 *
 * @author Usuario
 */
public class ClientHelper {

    public static Client fromForm(ClientView view) {
        String clientId = view.txtClientId.getText().trim();
        String userId = view.txtUserId.getText().trim();
        String companyName = view.txtCompanyName.getText().trim();
        String contactNumber = view.txtContactNumber.getText().trim();

        return new Client(clientId, userId, companyName, contactNumber);
    }
}
