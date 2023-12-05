package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresSQLConnection {
    private static final String URL = "jdbc:postgresql://examenad.ci66saah1axn.us-east-1.rds.amazonaws.com:5432/arrsan";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "qwerty1234";

    public Connection postgresSQLConect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
