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
import java.util.List;

import static com.zdy.action.GetAllAction.queryAllInfo;
import static com.zdy.dao.InfoDao.queryAll;

/**
 * Created by zdy on 2017/1/24.
 */
@WebServlet(name = "GetAllServlet",urlPatterns = "/GetAllServlet")
public class GetAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String id= (String) session.getAttribute("id");
        PrintWriter out=response.getWriter();
        if (id==null){
            out.print("signout");
            return;
        }
        try {
            String str=queryAllInfo();
            System.out.println(str);
            out.print(str);
        } catch (Exception e) {
            e.printStackTrace();
            out.print("error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

}
