package org.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Component
public class DatabaseSQLConnector {
    @Value("${sql-database-connector.host}")
    private String databaseHost;
    @Value("${sql-database-connector.name}")
    private String databaseName;
    @Value("${sql-database-connector.credentials.user}")
    private String databaseUser;

    public void connect() {

        String url =
                "jdbc:sqlserver://localhost\\SQLEXPRESS"
                        + ";databaseName=pubs"
                        + ";integratedSecurity=true"
                        + ";encrypt=true"
                        + ";trustServerCertificate=true";

        try (Connection connection = DriverManager.getConnection(url)) {

            System.out.println("Database connection established!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}