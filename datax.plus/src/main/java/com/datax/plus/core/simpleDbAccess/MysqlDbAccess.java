package com.datax.plus.core.simpleDbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDbAccess {

    private String userName = "root";
    private String password = "root";
    private String url = "jdbc:mysql://localhost【数据库地址】:3306【端口/数据库名称】?serverTimezone=UTC";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  MysqlDbAccess(String url, String userName, String password){
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
//        if (rs != null) {
//            try {
//                rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if (stmt != null) {
//            try {
//                stmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
