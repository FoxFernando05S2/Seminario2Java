package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/project_tise";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            // Puedes quitar el println si no quieres ver cada conexión nueva
            System.out.println("Conexión exitosa.");
            return conn;
        } catch (SQLException e) {
            System.err.println("Error de conexión a la base de datos.");
            e.printStackTrace();
            return null;
        }
    }
}
