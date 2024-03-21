package org.example.DAO.Connectionfactory;

import lombok.Getter;
import org.example.DAO.DatabaseConfig.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class ConnectionFactory {
    private static final String JDBC_URL = DatabaseConfig.getJdbcUrl();
    private static final String JDBC_USERNAME = DatabaseConfig.getJdbcUsername();
    private static final String JDBC_PASSWORD = DatabaseConfig.getJdbcPassword();

    private static ConnectionFactory instance;
    private Connection connection;

    private ConnectionFactory() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance() {
        if (instance == null) {
            synchronized (ConnectionFactory.class) {
                if (instance == null) {
                    instance = new ConnectionFactory();
                }
            }
        }
        return instance;
    }

}
