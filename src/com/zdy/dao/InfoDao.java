package com.zdy.dao;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zdy.db.DButil;
import com.zdy.model.InfoModel;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zdy on 2017/1/9 0009.
 */
public class InfoDao {
    public void insert(String id) throws Exception {
        DButil dButil=new DButil();
        Connection connection= dButil.getConnection();
        String sql="insert into info(id) values(?)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        preparedStatement.execute();
        dButil.closeConnection(connection,preparedStatement,null);
    }
    public static boolean exist(String id) throws Exception {//不存在时返回false
        DButil dButil=new DButil();
        Connection connection= dButil.getConnection();
        String sql="select * from info where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        if (resultSet.next()){
            dButil.closeConnection(connection,preparedStatement,resultSet);
            return true;
        }else{
            dButil.closeConnection(connection,preparedStatement,resultSet);
            return false;
        }
    }
    public static InfoModel query(String id) throws Exception {
        DButil dButil=new DButil();
        Connection connection= dButil.getConnection();
        String sql="select * from info where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        List<InfoModel> infoModelList=new ArrayList<InfoModel>();
        InfoModel infoModel=null;
        while(resultSet.next()){
            infoModel=new InfoModel();
            infoModel.setName(resultSet.getString("name"));
            infoModel.setEmail(resultSet.getString("email"));
            infoModel.setTel(resultSet.getString("tel"));
            infoModel.setQq(resultSet.getString("qq"));
            infoModel.setWechat(resultSet.getString("wechat"));
            java.util.Date birthday=null;
            String birth=resultSet.getString("birthday");
            if (birth!=null){
                birthday= new SimpleDateFormat("yyyy-MM-dd").parse(birth);
            }
            infoModel.setBirthday(birthday);
            infoModel.setCountry(resultSet.getString("country"));
            infoModel.setProvince(resultSet.getString("province"));
            infoModel.setCity(resultSet.getString("city"));
            infoModel.setUniversity(resultSet.getString("university"));
            infoModel.setAcademy(resultSet.getString("academy"));
            infoModel.setCompany(resultSet.getString("company"));
            infoModel.setOccupation(resultSet.getString("occupation"));
            infoModelList.add(infoModel);
        }
        dButil.closeConnection(connection,preparedStatement,null);
        return infoModelList.get(0);
    }
    public boolean update(InfoModel infoModel)  {
        DButil dButil=new DButil();
        Connection connection= null;
        try {
            connection = dButil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Timestamp updatetime= new java.sql.Timestamp(new java.util.Date().getTime());
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("update info set ");
        stringBuilder=build(stringBuilder,"name",infoModel.getName());
        stringBuilder=build(stringBuilder,"email",infoModel.getEmail());
        stringBuilder=build(stringBuilder,"tel",infoModel.getTel());
        stringBuilder=build(stringBuilder,"qq",infoModel.getQq());
        stringBuilder=build(stringBuilder,"wechat",infoModel.getWechat());
        String birthday=null;
        if (infoModel.getBirthday()==null){
        }else{
            birthday=new SimpleDateFormat("yyyy-MM-dd").format(infoModel.getBirthday());
        }
        stringBuilder=build(stringBuilder,"birthday",birthday);
        //stringBuilder=build(stringBuilder,"birthday",new SimpleDateFormat("yyyy-MM-dd").format(infoModel.getBirthday()));
        stringBuilder=build(stringBuilder,"country",infoModel.getCountry());
        stringBuilder=build(stringBuilder,"province",infoModel.getProvince());
        stringBuilder=build(stringBuilder,"city",infoModel.getCity());
        stringBuilder=build(stringBuilder,"university",infoModel.getUniversity());
        stringBuilder=build(stringBuilder,"academy",infoModel.getAcademy());
        stringBuilder=build(stringBuilder,"company",infoModel.getCompany());
        stringBuilder=build(stringBuilder,"occupation",infoModel.getOccupation());
        stringBuilder.append(" updatetime='"+updatetime+"'");
        stringBuilder.append(" where id='"+infoModel.getId()+"'");
        System.out.println("toString"+stringBuilder.toString());
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = connection.prepareStatement(stringBuilder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*String sql="update info set name=?,email=?,tel=?,qq=?,wechat=?,birthday=?,country=?,province=?,city=?,university=?,academy=?,company=?,occupation=?" +
                "where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,infoModel.getName());
        preparedStatement.setString(2,infoModel.getEmail());
        preparedStatement.setString(3,infoModel.getTel());
        preparedStatement.setString(4,infoModel.getQq());
        preparedStatement.setString(5,infoModel.getWechat());
        java.sql.Date date= new java.sql.Date(infoModel.getBirthday().getTime());
        preparedStatement.setDate(6,date);
        preparedStatement.setString(7,infoModel.getCountry());
        preparedStatement.setString(8,infoModel.getProvince());
        preparedStatement.setString(9,infoModel.getCity());
        preparedStatement.setString(10,infoModel.getUniversity());
        preparedStatement.setString(11,infoModel.getAcademy());
        preparedStatement.setString(12,infoModel.getCompany());
        preparedStatement.setString(13,infoModel.getOccupation());
        preparedStatement.setString(14,infoModel.getId());*/
        try {
            preparedStatement.execute();
            dButil.closeConnection(connection,preparedStatement,null);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            dButil.closeConnection(connection,preparedStatement,null);
            return false;
        }
    }
    public static List<InfoModel> queryAll() throws Exception {
        DButil dButil=new DButil();
        Connection connection= dButil.getConnection();
        String sql="select * from info ";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ResultSet resultSet=preparedStatement.executeQuery();
        List<InfoModel> infoModelList=new ArrayList<InfoModel>();
        InfoModel infoModel=null;
        while(resultSet.next()){
            infoModel=new InfoModel();
            infoModel.setId(resultSet.getString("id"));
            infoModel.setName(resultSet.getString("name"));
            infoModel.setEmail(resultSet.getString("email"));
            infoModel.setTel(resultSet.getString("tel"));
            infoModel.setQq(resultSet.getString("qq"));
            infoModel.setWechat(resultSet.getString("wechat"));
            java.util.Date birthday=null;
            String birth=resultSet.getString("birthday");
            if (birth!=null){
                birthday= new SimpleDateFormat("yyyy-MM-dd").parse(birth);
            }
            infoModel.setBirthday(birthday);
            infoModel.setCountry(resultSet.getString("country"));
            infoModel.setProvince(resultSet.getString("province"));
            infoModel.setCity(resultSet.getString("city"));
            infoModel.setUniversity(resultSet.getString("university"));
            infoModel.setAcademy(resultSet.getString("academy"));
            infoModel.setCompany(resultSet.getString("company"));
            infoModel.setOccupation(resultSet.getString("occupation"));
            infoModelList.add(infoModel);
        }
        dButil.closeConnection(connection,preparedStatement,null);
        return infoModelList;
    }
    private StringBuilder build(StringBuilder stringBuilder, String attr,String val) {
        if (val==null||val.equals("")){
            stringBuilder.append(attr+"=null"+", ");
            return stringBuilder;
        }
        stringBuilder.append(attr+"='"+val+"'"+", ");
        return stringBuilder;
    }
}
