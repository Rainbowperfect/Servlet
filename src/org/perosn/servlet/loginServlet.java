package org.perosn.servlet;

import org.perosn.dao.UserDao;
import org.perosn.pojo.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 面向对象设计的六大基本原则
 * 单一职责原则，开放封闭原则(对拓展开放对修改封闭),依赖倒置(依赖抽象不依赖具体),迪米特法则，里氏替换原则，接口隔离原则
 */
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //2.到数据库中查询用户
        UserDao dao = new UserDao();
        User user = dao.findUser(username, password);
        //获取ServletContext对象
        ServletContext servletContext = getServletContext();
        if (user != null) {
            Integer obj = (Integer) servletContext.getAttribute("count");
            int count = 0;
            if (obj != null) {
                count = obj;
            }
            //登录成功之后，将count+1
            count++;
            //将这个count存放到ServletContext中
            servletContext.setAttribute("count", count);
            //设置响应跳转到success.html

            response.setStatus(302);
            response.setHeader("Location", "suffix.jsp");
        } else {
            System.out.println("登录失败");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
