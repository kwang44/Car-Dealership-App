package com.dealership.db;

import java.sql.*;

public class JDBCConnection {
    private static JDBCConnection JDBCConnectionInstance = null;

    private JDBCConnection() {}

    public static JDBCConnection getInstance() {
        if (JDBCConnectionInstance == null)
            JDBCConnectionInstance = new JDBCConnection();
        return JDBCConnectionInstance;
    }

    public Connection getConnnection() throws SQLException {
        String url = "jdbc:postgresql://database-1.cyftucwz0ops.us-east-1.rds.amazonaws.com/enterprise?currentSchema=car_dealership";
        String username = "kevin";
        String password = "123456";

        Connection conn = DriverManager.getConnection(url, username, password);
        conn.setSchema("car_dealership");
        return conn;
    }

}