package com.zdy.action;

import com.zdy.dao.BaseDao;
import com.zdy.model.BaseModel;


/**
 * Created by zdy on 2017/1/5 0005.
 */
public class BaseAction {
   public static void signup(String id,String pwd) throws Exception {
       BaseDao baseDao = new BaseDao();
       BaseModel baseModel = new BaseModel();
       baseModel.setId(id);
       baseModel.setPwd(pwd);
       baseDao.add(baseModel);
   }
    public static boolean signin(String id,String pwd) throws Exception {
        BaseDao baseDao=new BaseDao();
        BaseModel baseModel=new BaseModel();
        baseModel.setId(id);
        baseModel.setPwd(pwd);
        return baseDao.query(baseModel);
    }

/*    public static void main(String[] args) throws Exception {
        System.out.println(signin("20122430238","123456"));
    }*/
}
