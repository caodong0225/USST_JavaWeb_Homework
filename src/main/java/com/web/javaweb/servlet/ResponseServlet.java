package com.web.javaweb.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * <p>
 *     USST课程《JavaWeb开发技术》的第一个Servlet程序
 * </p>
 * @author jyzxc
 * @since 2024-09-29
 */
@WebServlet(name = "ResponseServlet", value = "/result")
public class ResponseServlet extends HttpServlet {
    public void init() {

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置响应的内容类型，例如文本类型
        response.setContentType("text/plain;charset=UTF-8");
        // 获取响应的输出流并写入响应数据
        response.getWriter().write("检索功能暂未实现");
    }
}
