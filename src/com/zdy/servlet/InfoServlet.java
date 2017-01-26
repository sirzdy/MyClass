package com.zdy.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.zdy.action.InfoAction.edit;

/**
 * Created by zdy on 2017/1/10 0010.
 */
@WebServlet(name = "InfoServlet",urlPatterns = "/InfoServlet")
public class InfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        HttpSession session = request.getSession();
        String id= (String) session.getAttribute("id");
        if (id==null){
            out.print("signout");
            return;
        }
        System.out.println(id);
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String tel=request.getParameter("tel");
        String qq=request.getParameter("qq");
        String wechat=request.getParameter("wechat");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date birthday= null;
        try {
            birthday = simpleDateFormat.parse(request.getParameter("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String country=request.getParameter("country");
        String province=request.getParameter("province");
        String city=request.getParameter("city");
        String university=request.getParameter("university");
        String academy=request.getParameter("academy");
        String company=request.getParameter("company");
        String occupation=request.getParameter("occupation");
        try {
            if ( edit(id, name, email, tel, qq, wechat, birthday,country,province,city,university,academy,company,occupation)){
                out.print("success");
            }else{
                out.print("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("fail");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
