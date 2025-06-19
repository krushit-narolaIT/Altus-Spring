package com.krushit.common.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConfig {
    private final String dbUrl;
    private final String dbUsername;
    private final String dbPassword;
    private final String dbDriver;

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public DBConfig(String url, String username, String password, String driver) {
        this.dbUrl = url;
        this.dbUsername = username;
        this.dbPassword = password;
        this.dbDriver = driver;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(this.dbDriver);
        return DriverManager.getConnection(this.dbUrl, this.dbUsername, this.dbPassword);
    }
}
