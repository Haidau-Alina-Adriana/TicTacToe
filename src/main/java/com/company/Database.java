package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "student";
    private static final String PASSWORD = "password";
    private static Connection connection = null;

    private Database() {
    }

    private static Connection createConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public static Connection getConnection() {
        if (connection == null)
            connection = createConnection();
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

}