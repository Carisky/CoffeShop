package org.example.DatabaseConfig;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {
    private static final String CONFIG_FILE = "config.properties";

    @Getter
    private static String jdbcUrl;
    @Getter
    private static String jdbcUsername;
    @Getter
    private static String jdbcPassword;

    static {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);

            jdbcUrl = properties.getProperty("database.url");
            jdbcUsername = properties.getProperty("database.user");
            jdbcPassword = properties.getProperty("database.password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

