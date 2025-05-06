package Model.Entities;

import java.sql.Date;

public class Payment {
    private String paymentId;
    private String clientId;
    private double amount;
    private Date paymentDate;  // Usamos java.sql.Date para manejo correcto con BD
    private String status;

    public Payment(String paymentId, String clientId, double amount, Date paymentDate, String status) {
        this.paymentId = paymentId;
        this.clientId = clientId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    // Getters y setters
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
