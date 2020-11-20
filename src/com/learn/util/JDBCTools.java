package com.learn.util;

//封装需要复用的jdbc代码

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTools {
    private static Connection conn;
    private static ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
    private static String url = bundle.getString("url");
    private static String user = bundle.getString("user");
    private static String password = bundle.getString("password");

    //把只需要执行一次的代码（获取driver）放到静态代码块中
    static {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
            String driver = bundle.getString("driver");
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 定义一个数据库连接池方法，返回数据库连接池
     * @return 数据库连接池
     */
    public static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 定义一个静态方法释放资源，传入值为需要释放的资源参数
     * @param conn
     * @param statement
     * @param rs 如果不存在则传入null
     */
    public static void release(Connection conn, Statement statement, ResultSet rs) {
        try {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
