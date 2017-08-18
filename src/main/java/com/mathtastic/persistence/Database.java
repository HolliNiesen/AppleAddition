package com.mathtastic.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Database accesses the mathtastic database.
 * Created by Holli on 6/27/2017.
 */
public class Database {

    private static Database instance = new Database();
    private Properties properties;
    private Connection connection;

    /**
     * Constructor for the database
     */
    private Database() {
        loadProperties();
    }

    /**
     * Gets the database available
     * @return the database instance
     */
    public static Database getInstance() {
        return instance;
    }

    /**
     * Gets the connection to the databse
     * @return the connection available
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Loads the database properties
     */
    private void loadProperties() {
        properties = new Properties();

        try {
            properties.load (this.getClass().getResourceAsStream("/database.properties"));

        } catch (IOException ioe) {
            System.out.println("Database.loadProperties()...Cannot load the properties file");
            ioe.printStackTrace();

        } catch (Exception e) {
            System.out.println("Database.loadProperties()..." + e);
            e.printStackTrace();
        }
    }

    /**
     * Connects to the database
     * @throws Exception if connection fails
     */
    public void connect() throws Exception {
        try {
            String driver = properties.getProperty("driver");
            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            throw new Exception("Database.connect()... Error: MySQL Driver not found");
        }

        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        connection = DriverManager.getConnection(url, username,  password);
    }

    /**
     * Disconnects from the database
     * @throws Exception if disconnection fails
     */
    public void disconnect() throws Exception {
        if (connection == null) {
            return;
        }
        try {
            connection.close();

        } catch (SQLException e) {
            throw new Exception("Cannot close connection");
        }
    }
}
