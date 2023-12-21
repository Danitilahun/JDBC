import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserOperations {
	static void createDatabase(Connection connection, String databaseName) {
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
 
 static void useDatabase(Connection connection, String databaseName) {
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
 
static void createTableUser(Connection connection) {
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
    static void insertUserData(Connection connection, String username, String email, String password) {
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
    static void insertMultipleUsers(Connection connection) {
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
    
    static void selectAllUsers(Connection connection) {
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
   static void selectUser(Connection connection, String conditionType, String value) {
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
    
    static void updateUser(Connection connection ,String password , int Id) {
    	try {
    		String updateSQLQuery = "UPDATE User SET password = ? WHERE id = ?";
    		PreparedStatement preparedStatement = connection.prepareStatement(updateSQLQuery);
    		preparedStatement.setString(1, password);
    		preparedStatement.setInt(2, Id);
    		
    		int rowsAffected = preparedStatement.executeUpdate();
    		
    		
    		if (rowsAffected > 0) {
                System.out.println("Password updated successfully for user id: " + Id);
            } else {
                System.out.println("User not found or password not updated.");
            }
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
     static void deleteUserByID(Connection connection, int userId) {
        try {
            String deleteSQL = "DELETE FROM User WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, userId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User with ID " + userId + " deleted successfully.");
            } else {
                System.out.println("User not found or deletion failed.");
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
