package org.example.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String URL =
            "jdbc:sqlserver://localhost:1435;databaseName=PharmacyDB;encrypt=false;trustServerCertificate=true;loginTimeout=5;";
    private static final String USER = "AA";
    private static final String PASSWORD = "AA";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
