package com.example.demo.dao.dataservices;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostGreDatabaseConnector {

    public Connection getPostGreConnection(){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
             connection = DriverManager
                    .getConnection("jdbc:postgresql://43.205.203.68:5432/test",
                            "postgres", "iconcile");
            System.out.println("Opened database successfully");
            connection.setAutoCommit(false);
        }
        catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
      return connection;
    }
}
