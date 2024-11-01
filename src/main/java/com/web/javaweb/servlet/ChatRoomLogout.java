package com.web.javaweb.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

/**
 * @author jyzxc
 * @since 2024-11-01
 */
@WebServlet("/jsp/logout")
public class ChatRoomLogout extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 清空所有 session 属性
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();
        String userName = (String) session.getAttribute("userName");
        // 清空context里面的userName的值
        Set<String> userNames = (Set<String>) context.getAttribute("userNames");
        if (userNames != null && userName != null) {
            // 从集合中移除当前用户
            userNames.remove(userName);
        }
        // 清空session
        session.invalidate(); // 使 session 失效
        request.getRequestDispatcher("/jsp/ChatRoomLoginServlet").forward(request, response);
    }
}
