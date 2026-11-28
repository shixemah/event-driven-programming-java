package com.university;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This class handles the connection to the database
public class DatabaseUtil {

    // The URL of the database. "university_db" is the name we created in Phase 1
    private static final String URL = env("DB_URL", "jdbc:mysql://localhost:3306/university_db");

    // The default username for MySQL
    private static final String USER = env("DB_USER", "root");

    // CHANGE THIS TO YOUR ACTUAL MYSQL PASSWORD - but do it on YOUR computer only.
    // The guide puts the password straight into this file. If you push that file to a PUBLIC GitHub
    // repo, everyone can read your password. Instead, set an environment variable before running:
    //     Windows (Command Prompt):  set DB_PASSWORD=my_real_password
    //     then:                      mvn clean javafx:run
    // The text below is only a placeholder, so it is safe to commit.
    private static final String PASSWORD = env("DB_PASSWORD", "your_password");

    private static String env(String name, String fallback) {
        String value = System.getenv(name);
        return (value == null || value.isEmpty()) ? fallback : value;
    }

    // This method returns a Connection object. It throws SQLException if it fails.
    public static Connection getConnection() throws SQLException {
        // DriverManager attempts to connect to the database using the URL, USER, and PASSWORD
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
