import userManagement.StudentManagementSystem;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class App {

    public static void runApp() {
        StudentManagementSystem.getInstance().startSystem();
    }
    public static void main(String[] args) {
        runApp();

        String url = "";
        String user = "";
        String password = "";
        Properties prop = new Properties();
        try (InputStream input = App.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            // Load a properties file from class path, inside static method
            prop.load(input);

            // Get values from properties file
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.username");
            password = prop.getProperty("db.password");

            // Use the values as needed
            // ...
        } catch (Exception e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Establish database connection
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database successfully");

            // Execute SELECT query
            String query = "SELECT * FROM Flight";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            // Process result set
            while (rs.next()) {
                System.out.println("Flight airline: " + rs.getString("airline"));
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to the database or executing query");
            e.printStackTrace();
        } finally {
            // Close resources in the reverse order of their creation
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
