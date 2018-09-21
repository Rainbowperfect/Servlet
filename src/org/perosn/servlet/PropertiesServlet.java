package org.perosn.servlet;
/**
 * ${DESCRIPTION}
 * @author RainbowPerferct
 * @create 2018/9/20/23:59
 */

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 *  ${DESCRIPTION}
 *  @author RainbowPerferct/zero
 *
 *
 */
@WebServlet(name = "PropertiesServlet")
public class PropertiesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取真实的路径
        ServletContext servletContext = getServletContext();
        String realPath = servletContext.getRealPath("/src/user.properties");
        //读取properties文件
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(realPath);
        properties.load(fileInputStream);

        String name = properties.getProperty("name");
        System.out.println(name);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
