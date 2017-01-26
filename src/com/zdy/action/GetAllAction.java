package com.zdy.action;

import com.zdy.model.InfoModel;

import java.text.SimpleDateFormat;
import java.util.List;

import static com.zdy.dao.InfoDao.queryAll;

/**
 * Created by zdy on 2017/1/24.
 */
public class GetAllAction {
    public static String queryAllInfo() throws Exception {
        List<InfoModel> infoModels=queryAll();
        StringBuilder stringBuilder=new StringBuilder();
        String empty="";
        for ( InfoModel infoModel:infoModels){
            stringBuilder.append("<tr>");
            stringBuilder.append(build(infoModel.getId(),null,null));
            stringBuilder.append(build(infoModel.getName(),null,null));
            stringBuilder.append(build(infoModel.getEmail(),null,null));
            stringBuilder.append(build(infoModel.getTel(),null,null));
            stringBuilder.append(build(infoModel.getQq(),null,null));
            stringBuilder.append(build(infoModel.getWechat(),null,null));
            String birthday="";
            if(infoModel.getBirthday()!=null){
                birthday=new SimpleDateFormat("yyyy-MM-dd").format(infoModel.getBirthday());
            }
            stringBuilder.append(build(birthday,null,null));
            stringBuilder.append(build(infoModel.getCountry(),infoModel.getProvince(),infoModel.getCity()));
            stringBuilder.append(build(infoModel.getUniversity(),infoModel.getAcademy(),null));
            stringBuilder.append(build(infoModel.getCompany(),infoModel.getOccupation(),null));
            stringBuilder.append("</tr>");

        }
        return stringBuilder.toString();
    }

    public static String build(String str1,String str2,String str3){

        if (str1==null||str1.equals("")){
            str1="";
        }
        if (str2==null||str2.equals("")){
            str2="";
        }
        if (str3==null||str3.equals("")){
            str3="";
        }
        String str=str1+" "+str2+" "+str3;
        return "<td>"+str+"</td>";
    }

}
