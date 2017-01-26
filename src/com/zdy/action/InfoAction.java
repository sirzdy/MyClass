package com.zdy.action;

import com.zdy.dao.InfoDao;
import com.zdy.model.InfoModel;

import java.util.Date;

import static com.zdy.dao.InfoDao.exist;

/**
 * Created by zdy on 2017/1/9 0009.
 */
public class InfoAction {
    public static boolean edit(String id, String name, String email, String tel, String qq, String wechat, Date birthday,String country,String province,String city,String university,String academy,String company,String occupation)  {
        InfoDao infoDao=new InfoDao();
        InfoModel infoModel=new InfoModel();
        infoModel.setId(id);
        infoModel.setName(name);
        infoModel.setEmail(email);
        infoModel.setTel(tel);
        infoModel.setQq(qq);
        infoModel.setWechat(wechat);
        infoModel.setBirthday(birthday);
        infoModel.setCountry(country);
        infoModel.setProvince(province);
        infoModel.setCity(city);
        infoModel.setUniversity(university);
        infoModel.setAcademy(academy);
        infoModel.setCompany(company);
        infoModel.setOccupation(occupation);
        try {
            if (!exist(id)){
                infoDao.insert(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            if(infoDao.update(infoModel)){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
