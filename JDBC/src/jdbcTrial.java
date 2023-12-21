import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
//	                createTableUser(connection);
//	                insertUserData(connection, "JohnDoe", "john@example.com", "pass123");
//	                insertMultipleUsers(connection);
	                selectAllUsers(connection);
	                
	                selectUser(connection, "password", "pass123");
	                selectUser(connection, "email", "alice@example.com");
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
	// Function to insert data into the 'User' table
	    private static void insertUserData(Connection connection, String username, String email, String password) {
	        try {
	            String insertSQL = "INSERT INTO User (username, email, password) VALUES (?, ?, ?)";
	            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
	            preparedStatement.setString(1, username);
	            preparedStatement.setString(2, email);
	            preparedStatement.setString(3, password);

	            int rowsAffected = preparedStatement.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Data inserted successfully.");
	            } else {
	                System.out.println("Failed to insert data.");
	            }
	            preparedStatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	 // Function to insert multiple users into the 'User' table using Statement
	    private static void insertMultipleUsers(Connection connection) {
	        try {
	            Statement statement = connection.createStatement();

	            String[] usersData = {
	                    "INSERT INTO User (username, email, password) VALUES ('Alice', 'alice@example.com', 'pass123')",
	                    "INSERT INTO User (username, email, password) VALUES ('Bob', 'bob@example.com', 'pass456')",
	                    "INSERT INTO User (username, email, password) VALUES ('Charlie', 'charlie@example.com', 'pass789')"
	                   
	            };

	            for (String userData : usersData) {
	                statement.addBatch(userData);
	            }

	            int[] rowsAffected = statement.executeBatch();

	            System.out.println("Rows affected: " + rowsAffected.length);

	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    private static void selectAllUsers(Connection connection) {
	        try {
	            Statement statement = connection.createStatement();
	            String selectSQL = "SELECT * FROM User";
	            ResultSet resultSet = statement.executeQuery(selectSQL);
	           
	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String username = resultSet.getString("username");
	                String email = resultSet.getString("email");
	                String password = resultSet.getString("password");

	                System.out.println("ID: " + id + ", Username: " + username + ", Email: " + email + ", Password: " + password);
	            }

	            resultSet.close();
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	 // Function to select user based on a specified condition and value
	    private static void selectUser(Connection connection, String conditionType, String value) {
	        try {
	            String selectSQL = "SELECT * FROM User WHERE " + conditionType + " = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
	            preparedStatement.setString(1, value);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                // Retrieve user data and print
	                // Adjust column names as per your database schema
	                int userId = resultSet.getInt("id");
	                String username = resultSet.getString("username");
	                String email = resultSet.getString("email");
	                String password = resultSet.getString("password");

	                System.out.println("User ID: " + userId + ", Username: " + username + ", Email: " + email + ", Password: " + password);
	            }

	            resultSet.close();
	            preparedStatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
