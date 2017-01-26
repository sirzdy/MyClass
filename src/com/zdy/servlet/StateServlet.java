package com.zdy.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

import static com.zdy.dao.StateDao.updateAttr;


/**
 * Created by zdy on 2017/1/18.
 */
@WebServlet(name = "StateServlet",urlPatterns = "/StateServlet")
public class StateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        HttpSession session = request.getSession();
        String id= (String) session.getAttribute("id");
        String type=request.getParameter("type");
        String attr=request.getParameter("attr");
        if (id==null) {
            out.print("signout");
            return;
        }
        try {
            updateAttr(id, type, attr);
            out.print("success");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("fail");
        }

    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
