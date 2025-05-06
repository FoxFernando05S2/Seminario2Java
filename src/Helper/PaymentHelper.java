/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import Model.Entities.Payment;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class PaymentHelper {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static double parseAmount(String amountStr) {
        return Double.parseDouble(amountStr);
    }

    public static Date parseSqlDate(String dateStr) throws Exception {
        java.util.Date utilDate = dateFormat.parse(dateStr);
        return new Date(utilDate.getTime());
    }

    public static String formatDate(Date date) {
        return date != null ? dateFormat.format(date) : "";
    }

    public static Object[] toTableRow(Payment p) {
        return new Object[]{
            p.getPaymentId(),
            p.getClientId(),
            p.getAmount(),
            formatDate(p.getPaymentDate()),
            p.getStatus()
        };
    }
}
