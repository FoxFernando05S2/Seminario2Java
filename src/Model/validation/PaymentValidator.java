/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.validation;

import Model.Dao.PaymentDAO;
import Model.Entities.Payment;
import javax.swing.JOptionPane;
import view.PaymentView;

/**
 *
 * @author Usuario
 */
public class PaymentValidator {

    public static boolean isEmpty(String... fields) {
        for (String field : fields) {
            if (field == null || field.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidAmount(String amountStr) {
        try {
            Double.parseDouble(amountStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidDate(String dateStr) {
        try {
            new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
