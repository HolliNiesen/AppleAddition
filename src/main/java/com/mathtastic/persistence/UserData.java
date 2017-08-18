package com.mathtastic.persistence;

import com.mathtastic.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Sends user-related queries and updates to the database.
 * Created by Holli on 7/11/2017.
 */
public class UserData {

    /**
     * Gets a user by email from the database.
     * @param email The user's email.
     * @return The user in the database.
     */
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM User WHERE Email = \"" + email + "\";";
        return sendQuery(sql);
    }

    /**
     * Sends user related queries to the SQLData class.
     * @param sql The select statement.
     * @return The user requested.
     */
    private User sendQuery(String sql) {
        Database database = Database.getInstance();
        User user = null;
        SQLData sqlData = new SQLData();

        try {
            ResultSet results = sqlData.executeQuery(sql);
            if (results.next()) {
                user = createUserFromResults(results);
            }
            database.disconnect();

        } catch (SQLException e) {
            System.out.println("UserData.getUserByEmail()...SQLException: " + e);
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println("UserData.getUserByEmail()...Exception: " + e);
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Adds a new user to the database.
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     * @param email The user's email.
     * @param dateOfBirth The user's date of birth.
     * @param password The user's password.
     */
    public void createNewUser(String firstName, String lastName, String email, String dateOfBirth, String password) {
        String sql = "INSERT INTO User (FirstName, LastName, Email, DateOfBirth, Password) "
                + "VALUES ('" + firstName + "', '" + lastName + "', '" + email + "', '" + dateOfBirth + "', '" + password + "');";

        SQLData sqlData = new SQLData();
        sqlData.executeUpdate(sql);
    }

    /**
     * Updates a single column for a user.
     * @param column The column name.
     * @param value The value to update.
     * @param email The user's email.
     */
    public void updateColumn(String column, String value, String email) {
        String sql = "UPDATE User SET " + column + " = \"" + value + "\" WHERE Email = \"" + email + "\";";

        SQLData sqlData = new SQLData();
        sqlData.executeUpdate(sql);
    }

    /**
     * Creates a user from query results.
     * @param results The query results.
     * @return The user created.
     * @throws SQLException If the results do not match.
     */
    private User createUserFromResults(ResultSet results) throws SQLException {
        return new User(results.getInt("UserId"), results.getString("FirstName"),
                results.getString("LastName"), results.getString("Email"),
                results.getDate("DateOfBirth").toLocalDate(), results.getString("Password"));
    }
}
