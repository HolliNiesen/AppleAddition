package com.mathtastic.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Executes queries and updates.
 * Created by Holli on 7/14/2017.
 */
public class SQLData {
    private Database database;

    /**
     * Sends queries to the database.
     * @param sql The query.
     * @return The query results.
     * @throws SQLException If poor sql.
     */
    public ResultSet executeQuery(String sql) throws SQLException {
        ResultSet results = null;

        try {
            Connection connection = connectToDatabase();
            Statement selectStatement = connection.createStatement();
            results = selectStatement.executeQuery(sql);
            return results;

        } catch (Exception e) {
            System.out.println("SQLData.executeQuery()...Exception: " + e);
            e.printStackTrace();
        }
        return results;
    }

    /**
     * Sends updates to the database.
     * @param sql The update.
     */
    public void executeUpdate(String sql) {
        try {
            Connection connection = connectToDatabase();
            Statement updateStatement = connection.createStatement();
            updateStatement.executeUpdate(sql);
            database.disconnect();

        } catch (SQLException e) {
            System.out.println("SQLData.executeUpdate()...SQLException: " + e);
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println("SQLData.executeUpdate()...Exception: " + e);
            e.printStackTrace();
        }
    }

    /**
     * Connects to the database.
     * @return The database connection.
     * @throws Exception If unable to connect.
     */
    private Connection connectToDatabase() throws Exception {
        database = Database.getInstance();
        database.connect();
        return database.getConnection();
    }
}