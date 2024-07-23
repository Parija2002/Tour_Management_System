import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContactFormHandler {
    public static void main(String[] args) {
        if ("POST".equals(System.getenv("REQUEST_METHOD"))) {
            // Retrieve form data
            String name = System.getenv("name");
            String email = System.getenv("email");
            String phone = System.getenv("phone");
            String subject = System.getenv("subject");
            String message = System.getenv("message");

            // Database connection parameters
            String url = "jdbc:mysql://localhost:3306/your_database_name";
            String username = "your_username";
            String password = "your_password";

            // Insert data into the database
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String sql = "INSERT INTO contact_us (name, email, phone, subject, message) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, name);
                    statement.setString(2, email);
                    statement.setString(3, phone);
                    statement.setString(4, subject);
                    statement.setString(5, message);
                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
