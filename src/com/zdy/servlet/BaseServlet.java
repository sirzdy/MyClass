package com.zdy.servlet;

import com.zdy.action.BaseAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import static com.zdy.action.BaseAction.*;
import static com.zdy.dao.BaseDao.exist;

/**
 * Created by zdy on 2017/1/5 0005.
 */
@WebServlet(name = "BaseServlet",urlPatterns = "/BaseServlet")
public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String signtype=request.getParameter("signtype");
        /*注册或者登录*/
        if (signtype.equals("signin")||signtype.equals("signup")){
            String id=request.getParameter("id");
            String pwd=request.getParameter("pwd");
            PrintWriter out=response.getWriter();
            try {
                if (signtype.equals("signup")){//如果是注册
                    if (exist(id)){//如果用户已经存在
                        out.print("exist");
                    }else{//逐层成功,自动登录
                        signup(id,pwd);
                        out.print("success");
                        HttpSession session = request.getSession();
                        session.setAttribute("id",id);
                        //response.sendRedirect(request.getContextPath());
                    }
                }else if (signtype.equals("signin")){
                    if(signin(id,pwd)){
                        out.print("success");
                        HttpSession session = request.getSession();
                        session.setAttribute("id",id);
                        //response.sendRedirect(request.getContextPath());
                    }else{
                        out.print("fail");
                    }
                }
                /*Cookie cookieId=new Cookie("id",id);
                response.addCookie(cookieId);*/
                return;
            } catch (Exception e) {
                e.printStackTrace();
                out.print("fail");
                return;
            }
        }
        /*登出*/
        if (signtype.equals("signout")){
            HttpSession session =request.getSession();
            session.invalidate();
            //response.sendRedirect(request.getContextPath());
            return;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
