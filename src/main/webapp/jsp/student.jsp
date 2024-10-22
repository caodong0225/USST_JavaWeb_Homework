<%@ page import="com.web.javaweb.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生成绩管理</title>
</head>
<body>
<h2>学生成绩信息</h2>
<form action="SaveStudentGradesServlet" method="post">
    <table border="1">
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>专业</th>
            <th>平时成绩 (50%)</th>
            <th>期末成绩 (50%)</th>
            <th>总成绩</th>
            <th>操作</th>
        </tr>
        <%
            // 假设从Servlet传递过来的学生列表是request attribute，叫做studentList
            List<Student> studentList = (List<Student>) request.getAttribute("studentList");
            if (studentList != null) {
                for (Student student : studentList) {
        %>
        <tr>
            <td><%= student.getId() %></td>
            <td><%= student.getName() %></td>
            <td><%= student.getMajor() %></td>
            <td><input type="text" name="gradeMidterm_<%= student.getId() %>" value="<%= student.getGradeMidterm() %>"></td>
            <td><input type="text" name="gradeFinal_<%= student.getId() %>" value="<%= student.getGradeFinal() %>"></td>
            <td><input type="text" name="totalGrade_<%= student.getId() %>" value="<%= student.getTotalGrade() %>" readonly></td>
            <td><a href="DeleteStudentServlet?id=<%= student.getId() %>">删除</a></td>
        </tr>
        <%
                }
            }
            // 如果存在error信息，显示在页面上
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
        <tr>
            <td colspan="7"><%= error %></td>
        </tr>
        <%
            }
        %>
    </table>
    <br>
    <input type="submit" value="提交">
</form>

<br>
<a href="addStudent.jsp">新增学生</a>
</body>
</html>