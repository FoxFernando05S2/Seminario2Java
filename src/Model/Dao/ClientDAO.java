package Model.Dao;

import Model.Entities.Client;
import Model.Interface.CRUDInterface;
import Config.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements CRUDInterface<Client> {

    @Override
    public boolean insert(Client client) {
        String sql = "INSERT INTO clients (client_id, user_id, company_name, contact_number) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, client.getClientId());
            ps.setString(2, client.getUserId());
            ps.setString(3, client.getCompanyName());
            ps.setString(4, client.getContactNumber());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Client client) {
        String sql = "UPDATE clients SET user_id = ?, company_name = ?, contact_number = ? WHERE client_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, client.getUserId());
            ps.setString(2, client.getCompanyName());
            ps.setString(3, client.getContactNumber());
            ps.setString(4, client.getClientId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM clients WHERE client_id = ?";
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
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Client client = new Client(
                        rs.getString("client_id"),
                        rs.getString("user_id"),
                        rs.getString("company_name"),
                        rs.getString("contact_number")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Client getById(String id) {
        String sql = "SELECT * FROM clients WHERE client_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Client(
                        rs.getString("client_id"),
                        rs.getString("user_id"),
                        rs.getString("company_name"),
                        rs.getString("contact_number")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
