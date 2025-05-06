package Model.Dao;

import Model.Entities.Payment;
import Model.Interface.CRUDInterface;
import Config.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO implements CRUDInterface<Payment> {

    @Override
    public boolean insert(Payment payment) {
        String sql = "INSERT INTO payments (payment_id, client_id, amount, payment_date, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, payment.getPaymentId());
            ps.setString(2, payment.getClientId());
            ps.setDouble(3, payment.getAmount());
            ps.setDate(4, payment.getPaymentDate());  // Cambiado a setDate
            ps.setString(5, payment.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Payment payment) {
        String sql = "UPDATE payments SET client_id = ?, amount = ?, payment_date = ?, status = ? WHERE payment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, payment.getClientId());
            ps.setDouble(2, payment.getAmount());
            ps.setDate(3, payment.getPaymentDate());  // Cambiado a setDate
            ps.setString(4, payment.getStatus());
            ps.setString(5, payment.getPaymentId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM payments WHERE payment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Payment> getAll() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Payment payment = new Payment(
                        rs.getString("payment_id"),
                        rs.getString("client_id"),
                        rs.getDouble("amount"),
                        rs.getDate("payment_date"),  // Cambiado a getDate
                        rs.getString("status")
                );
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public Payment getById(String id) {
        String sql = "SELECT * FROM payments WHERE payment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Payment(
                        rs.getString("payment_id"),
                        rs.getString("client_id"),
                        rs.getDouble("amount"),
                        rs.getDate("payment_date"),  // Cambiado a getDate
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
