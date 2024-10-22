package com.web.javaweb.servlet;

import com.web.javaweb.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/jsp/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        List<Student> studentList = (List<Student>) getServletContext().getAttribute("studentList");
        if (studentList != null) {
            studentList.removeIf(student -> student.getId().equals(id));
        }
        request.setAttribute("studentList", studentList);
        session.setAttribute("studentList", studentList);  // 保存到 HttpSession
        request.getRequestDispatcher("/jsp/student.jsp").forward(request, response);
    }
}