package com.zdy.dao;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.zdy.db.DButil;
import com.zdy.model.InfoModel;
import com.zdy.model.StateModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zdy on 2017/1/18.
 */
public class StateDao {
    public static void updateAttr(String id,String field,String attr) throws Exception {
        DButil dButil=new DButil();
        Connection connection= dButil.getConnection();

        Boolean exist=false;
        /*查询是否存在*/
        String querySql="select * from state where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(querySql);
        preparedStatement.setString(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        if (resultSet.next()){
            exist=true;
        }
        System.out.println("是否存在"+exist);
        /*不存在时插入*/
        if(!exist){
            String insertSql="insert into state(id) values(?)";
            preparedStatement=connection.prepareStatement(insertSql);
            preparedStatement.setString(1,id);
            preparedStatement.execute();
        }
        String updateSql="update state set "+field+"=? where id=?";
        System.out.println(updateSql);
        System.out.println(attr);
        System.out.println(id);
        preparedStatement=connection.prepareStatement(updateSql);
        if (attr!=null&&!attr.equals("")){
            preparedStatement.setString(1,attr);
        }else{
            preparedStatement.setString(1,null);
        }
        preparedStatement.setString(2,id);
        preparedStatement.execute();
        dButil.closeConnection(connection,preparedStatement,null);

    }
    public static StateModel queryAttr(String id)  {
        DButil dButil = new DButil();
        Connection connection = null;
        try {
            connection = dButil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "select * from state where id=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            preparedStatement.setString(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //List<StateModel> stateModelList = new ArrayList<StateModel>();
        StateModel stateModel = null;
        try {
            while (resultSet.next()) {
                stateModel = new StateModel();
                String avatar="";
                String blog="";
                String signature="";
                String statement="";
                if (resultSet.getString("avatar")!=null){
                    avatar=resultSet.getString("avatar");
                }
                if (resultSet.getString("blog")!=null){
                    blog=resultSet.getString("blog");
                }
                if (resultSet.getString("signature")!=null){
                    signature=resultSet.getString("signature");
                }
                if (resultSet.getString("statement")!=null){
                    statement=resultSet.getString("statement");
                }
                stateModel.setAvatar(avatar);
                stateModel.setBlog(blog);
                stateModel.setSignature(signature);
                stateModel.setStatement(statement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stateModel;
    }
}
