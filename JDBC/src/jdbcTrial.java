import java.sql.Connection;


public class jdbcTrial {
	public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            System.out.println("Connected to the database!");

            // Perform operations using UserOperations class methods
             UserOperations.useDatabase(connection, "User");
             UserOperations.selectAllUsers(connection);
            // UserOperations.insertUserData(connection, "JohnDoe", "john@example.com", "pass123");
            // UserOperations.updateUser(connection, "newPassword", 1);
            // UserOperations.deleteUserByID(connection, 1);
            // ...
        } else {
            System.out.println("Failed to make connection!");
        }

        DatabaseConnection.closeConnection(connection);
    }
	 
}
