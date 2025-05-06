/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import Model.Entities.User;

/**
 *
 * @author Usuario
 */
public class UserHelper {

    public static Object[] toTableRow(User u) {
        return new Object[]{
            u.getUserId(),
            u.getName(),
            u.getEmail(),
            u.getPassword(),
            u.getRole()
        };
    }
}
