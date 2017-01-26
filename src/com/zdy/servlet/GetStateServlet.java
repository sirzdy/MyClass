package com.zdy.servlet;

import com.zdy.model.StateModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static com.zdy.dao.StateDao.queryAttr;

/**
 * Created by zdy on 2017/1/22.
 */
@WebServlet(name = "GetStateServlet",urlPatterns = "/GetStateServlet")
public class GetStateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        HttpSession session = request.getSession();
        String id= (String) ("id");
        if (id==null){
            out.print("signout");
            return;
        }
        String userid=request.getParameter("userid");
        try {
            StateModel stateModel=queryAttr(userid);
            if (stateModel==null){
                out.print("nodata");
                return;
            }
            String stateJson="{\"avatar\":\""+stateModel.getAvatar()+"\",\"blog\":\""+stateModel.getBlog()+"\",\"signature\":\""+ stateModel.getSignature()+"\",\"statement\":\""+ stateModel.getStatement()+"\"}";
            out.print(stateJson);
        } catch (Exception e) {
            //e.printStackTrace();
            out.print("nodata");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
