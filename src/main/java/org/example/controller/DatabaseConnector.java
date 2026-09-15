package org.example.controller;

import org.example.dao.RecordDao;
import org.example.entity.Record;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseConnector {
    @Value("${database-connector.host}")
    private String databaseHost;
    @Value("${database-connector.port}")
    private String databasePort;
    @Value("${database-connector.credentials.username}")
    private String databaseUser;
    @Value("${database-connector.credentials.password}")
    private String databasePassword;

    private RecordDao recordDao;

    public List<Record> executeSQL(String sql) {
        System.out.println(databaseHost + ":" + databasePort + "?ussername:" + databaseUser + "&password:" + databasePassword + "\nConnecting to database...");
        System.out.println("SQL: " + sql);
        System.out.println("Database connection established...");
        List<Record> l = new ArrayList<>();
        l.addAll(recordDao.getRecords());
        return l;
    }

}
