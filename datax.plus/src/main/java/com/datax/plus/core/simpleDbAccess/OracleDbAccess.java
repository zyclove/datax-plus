package com.datax.plus.core.simpleDbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDbAccess {

    private String userName = "";
    private String password = "";
    private String url = "";

    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public OracleDbAccess(String url, String userName, String password){
        this.userName = userName;
        this.password = password;
        this.url = url;
    }

    public static Connection getConnection(String url, String userName, String password) throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(url, userName, password);
        return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
