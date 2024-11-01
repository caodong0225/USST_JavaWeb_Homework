package com.web.javaweb.servlet;

import com.alibaba.fastjson.JSON;
import com.web.javaweb.model.Message;
import com.web.javaweb.model.ResponseData;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author jyzxc
 * @since 2024-11-01
 */
@WebServlet("/jsp/chat")
public class ChatServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置返回格式为json
        response.setContentType("application/json;charset=utf-8");

        // 获取请求参数
        List<Message> messages = (List<Message>) getServletContext().getAttribute("messages");
        if (messages == null) {
            messages = new ArrayList<>();
        }

        // 获取用户列表
        Set<String> userNames = (Set<String>) getServletContext().getAttribute("userNames");
        if(userNames == null) {
            userNames = new HashSet<>();
        }

        // 构建返回的数据结构
        ResponseData responseData = new ResponseData();
        responseData.setMessageList(messages);
        responseData.setSuccess(true);
        responseData.setUserList(userNames);

        // 使用 Fastjson 将数据转换为 JSON 格式并写入响应
        String jsonResponse = JSON.toJSONString(responseData);
        response.getWriter().write(jsonResponse);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String content = request.getParameter("content");
        String userName = (String) session.getAttribute("userName");
        String to = request.getParameter("to");
        Message message = new Message();
        message.setContent(content);
        message.setFrom(userName);
        message.setTo(to);
        ServletContext context = getServletContext();
        List<Message> messages = (List<Message>) context.getAttribute("messages");
        if (messages == null) {
            messages = new ArrayList<>();
        }
        messages.add(message);
        context.setAttribute("messages", messages);
        request.getRequestDispatcher("/jsp/chat.jsp").forward(request, response);
    }
}
