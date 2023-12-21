import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcTrial {
	 public static void main(String[] args) {
		 	// Driver 
		 	String driver = "com.mysql.cj.jdbc.Driver";
		 	
	        // JDBC URL for database 
	        String jdbcUrl = "jdbc:mysql://localhost:3306/";
	        
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
	                
	                // Create database
//	                createDatabase(connection , "User");
	                useDatabase(connection, "User");
	                createTableUser(connection);
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
	 
	 private static void createDatabase(Connection connection, String databaseName) {
	        try {
	            Statement statement = connection.createStatement();
	            String createDatabaseSQL = "CREATE DATABASE " + databaseName;
	            statement.executeUpdate(createDatabaseSQL);
	            System.out.println("Database '" + databaseName + "' created successfully.");
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 private static void useDatabase(Connection connection, String databaseName) {
		 try {
			 Statement statement = connection.createStatement();
			 String useDatabaseSQL = "USE " + databaseName;
			 statement.execute(useDatabaseSQL);
			 System.out.println("Using database: " + databaseName);
			 statement.close();
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 private static void createTableUser(Connection connection) {
	        try {
	            Statement statement = connection.createStatement();
	            String createTableSQL = "CREATE TABLE User ("
	                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
	                    + "username VARCHAR(50) NOT NULL,"
	                    + "email VARCHAR(100) NOT NULL,"
	                    + "password VARCHAR(50) NOT NULL"
	                    + ")";
	            statement.executeUpdate(createTableSQL);
	            System.out.println("Table 'User' created successfully.");
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
