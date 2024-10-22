package com.web.javaweb.servlet;

import com.web.javaweb.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * <p>
 *     登录Servlet
 * </p>
 * @author jyzxc
 */
@WebServlet(name = "RegisterServlet", value = "/register-servlet")
public class RegisterServlet extends HttpServlet {

    // 模拟一个用户列表来存储已注册的用户信息
    private static final ArrayList<User> users = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("RegisterServlet is working!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取表单数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));

        // 验证输入
        String errorMessage = null;
        if (username == null || username.isEmpty()) {
            errorMessage = "用户名不能为空";
        } else if (!password.equals(confirmPassword)) {
            errorMessage = "两次输入的密码不一致";
        } else if (age <= 0) {
            errorMessage = "年龄必须大于0";
        }

        if (errorMessage != null) {
            // 如果有错误信息，设置到request属性并转发回页面
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("/").forward(request, response);
            return;
        }

        // 如果验证通过，创建 User 对象并添加到用户列表
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setGender(gender);
        newUser.setAge(age);
        newUser.setRegistrationTime(LocalDateTime.now());

        // 将新用户添加到用户列表
        users.add(newUser);

        request.setAttribute("users", users);
        request.getRequestDispatcher("/").forward(request, response);
    }
}
