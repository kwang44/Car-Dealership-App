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
        String url = "";
        String username = "";
        String password = "";

        Connection conn = DriverManager.getConnection(url, username, password);
        conn.setSchema("car_dealership");
        return conn;
    }

}
