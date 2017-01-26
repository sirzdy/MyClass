package com.zdy.servlet;

import com.zdy.model.InfoModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import static com.zdy.dao.InfoDao.exist;
import static com.zdy.dao.InfoDao.query;

/**
 * Created by zdy on 2017/1/10 0010.
 */
@WebServlet(name = "GetInfoServlet",urlPatterns = "/GetInfoServlet")
public class GetInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        HttpSession session = request.getSession();
        String id= (String) session.getAttribute("id");
        if (id==null){
            out.print("signout");
            return;
        }

        try {
            if(exist(id)){
                InfoModel infoModel=query(id);
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append("{");
                stringBuilder.append("\"id\":\""+id+"\"");
                build(stringBuilder,"name",infoModel.getName());
                build(stringBuilder,"email",infoModel.getEmail());
                build(stringBuilder,"tel",infoModel.getTel());
                build(stringBuilder,"qq",infoModel.getQq());
                build(stringBuilder,"wechat",infoModel.getWechat());
                String birthday=null;
                if(infoModel.getBirthday()!=null){
                    birthday=new SimpleDateFormat("yyyy-MM-dd").format(infoModel.getBirthday());
                }
                build(stringBuilder,"birthday",birthday);
                build(stringBuilder,"country",infoModel.getCountry());
                build(stringBuilder,"province",infoModel.getProvince());
                build(stringBuilder,"city",infoModel.getCity());
                build(stringBuilder,"university",infoModel.getUniversity());
                build(stringBuilder,"academy",infoModel.getAcademy());
                build(stringBuilder,"company",infoModel.getCompany());
                build(stringBuilder,"occupation",infoModel.getOccupation());
                stringBuilder.append("}");
                if(sign!=false){
                    out.print(stringBuilder.toString());
                }else{
                    out.print("nodata");
                }
            }else{
                out.print("nodata");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Boolean sign=false;//传递消息为空时，标志为false
    private StringBuilder build(StringBuilder stringBuilder,String attr,String val) {
        if(val!=null&&!val.equals("")){
            sign=true;
            stringBuilder.append(",\""+attr+"\":"+"\""+val+"\"");
        }
        return stringBuilder;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
