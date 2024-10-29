package com.web.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Random;

/**
 * @author jyzxc
 * @since 20247-10-28
 */
@WebServlet("/jsp/code")
public class GenerateCodeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Random random = new Random();
        HttpSession session = request.getSession();
        // 生成随机数
        int code = random.nextInt(8) + 5;
        // 生成随机数对应的图片
        String imagePath = getServletContext().getRealPath("/resources/" + code + ".jpg");
        // 生成图片对应的base64编码
        try {
            // 读取图片并转换为字节数组
            byte[] imageBytes = Files.readAllBytes(new File(imagePath).toPath());

            // 将字节数组转化为Base64编码
            String base64String = Base64.getEncoder().encodeToString(imageBytes);

            // 输出Base64编码后的字符串
            session.setAttribute("error", "");
            session.setAttribute("code", code);
            session.setAttribute("base64String", base64String);
        } catch (Exception e) {
            // 出错时跳转到错误页面
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
        // 转发到验证码页面
        request.getRequestDispatcher("/jsp/code.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 判断验证码是否正确
        if (request.getParameter("code").equals(session.getAttribute("code").toString())) {
            session.setAttribute("error", "");
            request.getRequestDispatcher("/jsp/success.jsp").forward(request, response);
        } else {
            session.setAttribute("error", "验证码错误");
            request.getRequestDispatcher("/jsp/code.jsp").forward(request, response);
        }
    }
}
