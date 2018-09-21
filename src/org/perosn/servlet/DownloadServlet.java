package org.perosn.servlet; /**
 * ${DESCRIPTION}
 *
 * @author RainbowPerferct
 * @create 2018/9/21/0:31
 */

import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *
 *  ${DESCRIPTION}
 *  @author RainbowPerferct/zero
 *  @create 2018/9/21/0:31
 *
 */
@WebServlet(name = "DownloadServlet")
public class DownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //怎么来完成下载操作
        //1.获取客户端传过来的要进行下载的文件名
        String filename = request.getParameter("filename");
        //以下两句话解决GET请求的中文乱码
        byte[] bytes = filename.getBytes("ISO-8859-1");
        filename = new String(bytes, "UTF-8");


        //2.拼接下载路径
        String path = "download/"+filename;

        //3.下载其实事件文件读取成流，然后再通过输出流写给客户端
        //先将文件转换成流（用字节输入流）
        ServletContext servletContext = getServletContext();
        InputStream inputStream = servletContext.getResourceAsStream(path);

        //定义一个buffer
        byte[] buffer = new byte[1024];

        int len = -1;
        //read()方法的返回值如果是-1就表示读完了
        //获取往客户端写数据的字节输出流
        //让客户端弹出一个框，用于提示用户下载
        //响应头中不能够设置中文！！！
        //有中文的话，就要先将其进行编码处理
        String userAgent = request.getHeader("User-Agent");

        //只是针对响应头！！！！
        if (userAgent.contains("firefox")) {
            //就要使用BASE64进行编码
            filename = base64EncodeFileName(filename);
            System.out.println(filename);
        }else {
            //就使用URLEncoder进行编码
            filename = URLEncoder.encode(filename, "UTF-8");
            System.out.println(filename);
        }
        response.setHeader("Content-Disposition", "attachment;filename="+filename);

        ServletOutputStream outputStream = response.getOutputStream();
        while((len = inputStream.read(buffer)) != -1){
            //用字节输出流来往客户端写文件
            outputStream.write(buffer, 0, len);
        }
        //关流
        outputStream.close();
        inputStream.close();

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    /**
     * 作用，对文件名进行Base64的编码
     * @param fileName
     * @return
     */
    public static String base64EncodeFileName(String fileName) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try {
            return "=?UTF-8?B?"
                    + new String(base64Encoder.encode(fileName
                    .getBytes("UTF-8"))) + "?=";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
