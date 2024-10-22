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

@WebServlet("/jsp/SaveStudentGradesServlet")
public class SaveStudentGradesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Student> studentList = (List<Student>) session.getAttribute("studentList");
        if (studentList != null) {
            for (Student student : studentList) {
                String midtermParam = request.getParameter("gradeMidterm_" + student.getId());
                String finalParam = request.getParameter("gradeFinal_" + student.getId());

                if (midtermParam != null && finalParam != null) {
                    try {
                        double gradeMidterm = Double.parseDouble(midtermParam);
                        double gradeFinal = Double.parseDouble(finalParam);
                        student.setGradeMidterm(gradeMidterm);
                        student.setGradeFinal(gradeFinal);
                        student.setTotalGrade((gradeMidterm * 0.5) + (gradeFinal * 0.5));
                    } catch (NumberFormatException e) {
                        // 错误处理，比如提供错误反馈
                    }
                }
            }
        }
        session.setAttribute("studentList", studentList);  // 保存到 HttpSession
        request.setAttribute("studentList", studentList);
        request.getRequestDispatcher("/jsp/student.jsp").forward(request, response);
    }
}