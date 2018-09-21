package org.perosn.servlet; /**
 * ${DESCRIPTION}
 *
 * @author RainbowPerferct
 * @create 2018/9/20/22:54
 */

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 *  ${DESCRIPTION}
 *  @author RainbowPerferct/zero
 *  @create 2018/9/20/22:54
 *
 */
@WebServlet(name = "CountServlet")
public class CountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //登录成功次数存放在ServletContext对象中
        ServletContext servletContext = getServletContext();
        int count = (int) servletContext.getAttribute("count");
        //解决响应的中文乱码
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("登录成功次数为:"+count);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
