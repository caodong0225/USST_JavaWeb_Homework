<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>新增学生</title>
</head>
<body>
<h2>录入新的学生信息</h2>
<form action="SaveNewStudentServlet" method="post">
    <label>学号：</label><input type="text" name="studentId" required><br><br>
    <label>姓名：</label><input type="text" name="studentName" required><br><br>
    <label>专业：</label><input type="text" name="studentMajor" required><br><br>
    <label>平时成绩：</label><input type="text" name="gradeMidterm" value="0"><br><br>
    <label>期末成绩：</label><input type="text" name="gradeFinal" value="0"><br><br>
    <input type="submit" value="保存">
</form>
</body>
</html>