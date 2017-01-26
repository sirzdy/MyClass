package com.zdy.db;
import com.mysql.jdbc.Driver;
import java.sql.*;

/**
 * Created by zdy on 2017/1/5 0005.
 */
public class DButil {
    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String URL="jdbc:mysql://localhost:3306/myclass";
    private static final String USER="root";
    private static final String PASSWORD="PEAKshine1225!";
    public Connection getConnection() throws Exception{
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public void closeConnection(Connection conn,Statement stm,ResultSet rs){
        try {
            if (conn != null)
                conn.close();
            if (stm != null)
                stm.close();
            if (rs != null)
                rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
