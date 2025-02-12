package kz.aitu.restpro.restpro.dbconnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "070929652878";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection to database established successfully.");
            return connection;
        } catch (SQLException e) {
            System.out.println("Connection closed: " + e.getMessage());
            return null;
        }
    }
}