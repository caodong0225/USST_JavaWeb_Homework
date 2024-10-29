package com.web.javaweb.servlet;

import com.web.javaweb.utils.FileUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * @author jyzxc
 * @since 2024-10-29
 */
@WebServlet("/jsp/download")
public class FileDownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String referer = req.getHeader("Referer");
        // 设置防盗链，只可以从file.jsp页面下载
        if (referer == null || !referer.contains("http://localhost:8080/javaWeb_war_exploded/jsp/file.jsp")) {
            resp.getWriter().write("Forbidden!");
            resp.getWriter().close();
        } else {
            //设置请求编码格式
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            //获取参数  （得到要下载的文件名）
            String fileName = req.getParameter("filename");
            //参数的非空判断  trim:去除字符串的前后空格
            if (fileName == null || fileName.trim().isEmpty()) {
                resp.getWriter().write("请输入要下载的文件名！");
                resp.getWriter().close();
                return;
            }
            //得到文件存放的路径
            String path = req.getServletContext().getRealPath("/download/");
            //通过路径得到file对象
            File file = new File(path + fileName);
            //判断文件对象是否存在并且是否为标准文件
            if (file.exists() && file.isFile()) {
                // 校验文件的SHA256值，防止文件被篡改
                String sha256 = null;
                try {
                    sha256 = FileUtils.calculateSHA256(file);
                } catch (Exception ignored) {

                }
                if(sha256 == null || !sha256.equals(req.getParameter("sha256"))) {
                    resp.getWriter().write("文件已被篡改，请重试！");
                    resp.getWriter().close();
                    return;
                }
                //设置响应类型（浏览器无法使用某种方式或激活某个程序来处理的MIME类型）
                resp.setContentType("application/octet-stream");
                //设置响应头信息
                resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
                //得到file文件的输入流
                InputStream in = new FileInputStream(file);
                //得到字节输出流
                ServletOutputStream out = resp.getOutputStream();
                //定义byte数组
                byte[] bytes = new byte[1024];
                //定义长度
                int len;
                //循环输出
                while ((len = in.read(bytes)) != -1) {
                    //输出
                    out.write(bytes, 0, len);
                }

                //关闭资源
                out.close();
                in.close();

            } else {
                resp.getWriter().write("文件不存在，请重试！");
                resp.getWriter().close();
            }
        }

    }
}
