package org.example.database;

import org.example.Connectionfactory.ConnectionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class database {
    private final Connection connection;

    public database() {
        this.connection = ConnectionFactory.getInstance().getConnection();
    }

    public void executeSqlFile(String filePath, Connection connection) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sqlCommands = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sqlCommands.append(line);
                sqlCommands.append("\n");
            }

            try (PreparedStatement statement = connection.prepareStatement(sqlCommands.toString())) {
                statement.executeUpdate();
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void DROP(){
        executeSqlFile("dropTables.sql", connection);
    }
    public void CREATE_TABLES(){
        executeSqlFile("init-db.sql", connection);
    }
    public void INSERT_DATA(){
        executeSqlFile("insertTables.sql", connection);
    }

    public void REFRESH(){
        executeSqlFile("dropTables.sql", connection);
        executeSqlFile("init-db.sql", connection);
        executeSqlFile("insertTables.sql", connection);
    }

}
