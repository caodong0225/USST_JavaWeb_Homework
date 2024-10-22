package com.web.javaweb.servlet;

import com.web.javaweb.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jyzxc
 */
@WebServlet("/jsp/SaveNewStudentServlet")
public class SaveNewStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("studentId");
        String name = request.getParameter("studentName");
        String major = request.getParameter("studentMajor");
        String gradeMidtermParam = request.getParameter("gradeMidterm");
        String gradeFinalParam = request.getParameter("gradeFinal");

        double gradeMidterm = Double.parseDouble(gradeMidtermParam);
        double gradeFinal = Double.parseDouble(gradeFinalParam);
        double totalGrade = (gradeMidterm * 0.5) + (gradeFinal * 0.5);

        Student newStudent = new Student(id, name, major, gradeMidterm, gradeFinal, totalGrade);

        HttpSession session = request.getSession();
        List<Student> studentList = (List<Student>) session.getAttribute("studentList");
        if (studentList == null) {
            studentList = new ArrayList<>();
        }else {
            for(Student student : studentList){
                if(student.getId().equals(id)){
                    request.setAttribute("studentList", studentList);
                    request.setAttribute("error", "学号已经存在");
                    request.getRequestDispatcher("/jsp/student.jsp").forward(request, response);
                    return;
                }
            }
        }

        studentList.add(newStudent);
        request.setAttribute("studentList", studentList);
        session.setAttribute("studentList", studentList);
        request.getRequestDispatcher("/jsp/student.jsp").forward(request, response);
    }
}
