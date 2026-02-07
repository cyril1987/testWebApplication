package com.example.demo.dao.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostGreConnectivity {

    private static final Logger logger = Logger.getLogger(PostGreConnectivity.class.getName());

    public static void main(String[] args) {
        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");

        if (dbUrl == null || dbUser == null || dbPassword == null) {
            logger.severe("Database environment variables (DB_URL, DB_USERNAME, DB_PASSWORD) must be set.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            logger.info("Opened database successfully");
            connection.setAutoCommit(false);

            try (Statement insertStatement = connection.createStatement()) {
                String sqlInsert = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                        + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
                insertStatement.executeUpdate(sqlInsert);

                sqlInsert = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                        + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
                insertStatement.executeUpdate(sqlInsert);

                sqlInsert = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                        + "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
                insertStatement.executeUpdate(sqlInsert);

                sqlInsert = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                        + "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
                insertStatement.executeUpdate(sqlInsert);
            }

            connection.commit();
            logger.info("Records created successfully");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Database operation failed", e);
        }
    }
}
