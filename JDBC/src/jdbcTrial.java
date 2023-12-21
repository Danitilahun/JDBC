import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbcTrial {
	 public static void main(String[] args) {
		 	// Driver 
		 	String driver = "com.mysql.cj.jdbc.Driver";
		 	
	        // JDBC URL for database 
	        String jdbcUrl = "jdbc:mysql://localhost:3306/jdbctest";
	        
	        // Database credentials (replace with your username and password)
	        String username = "root";
	        String password = "t9aVMr592.L4";

	        // Connection object
	        Connection connection = null;

	        try {
	            // Register JDBC driver
	            Class.forName(driver);

	            // Open a connection
	            System.out.println("Connecting to database...");
	            connection = DriverManager.getConnection(jdbcUrl, username, password);

	            if (connection != null) {
	                System.out.println("Connected to the database!");
	                
	            } else {
	                System.out.println("Failed to make connection!");
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            // Handle errors
	            e.printStackTrace();
	        } finally {
	            try {
	                if (connection != null) {
	                    connection.close();
	                    System.out.println("Connection closed.");
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
