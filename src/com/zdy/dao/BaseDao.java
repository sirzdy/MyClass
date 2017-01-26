package com.zdy.dao;

import com.zdy.db.DButil;
import com.zdy.model.BaseModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zdy on 2017/1/5 0005.
 */
public class BaseDao {
    public static boolean exist(String id) throws Exception {//存在时返回true，不存在时返回false
        DButil dButil = new DButil();
        Connection connection = dButil.getConnection();
        String sql = "select * from base where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            dButil.closeConnection(connection, preparedStatement, resultSet);
            return true;
        } else {
            dButil.closeConnection(connection, preparedStatement, resultSet);
            return false;
        }
    }
    public void add(BaseModel baseModel) throws Exception {
        DButil dButil=new DButil();
        Connection connection= dButil.getConnection();
        String sql="insert into base(id,pwd) values(?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,baseModel.getId());
        preparedStatement.setString(2,baseModel.getPwd());
        preparedStatement.execute();
        dButil.closeConnection(connection,preparedStatement,null);
    }
    public boolean query(BaseModel baseModel) throws Exception {
        DButil dButil=new DButil();
        Connection connection= dButil.getConnection();
        String sql="select pwd from base where id=? and pwd=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,baseModel.getId());
        preparedStatement.setString(2,baseModel.getPwd());
        ResultSet resultSet=preparedStatement.executeQuery();
        if (resultSet.next()){
            dButil.closeConnection(connection,preparedStatement,resultSet);
            return true;
        }else{
            dButil.closeConnection(connection,preparedStatement,resultSet);
            return false;
        }
    }
}