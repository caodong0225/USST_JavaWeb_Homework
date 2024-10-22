<%@ page import="com.web.javaweb.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .register-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }
        h1 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
        }
        input[type="text"], input[type="password"], input[type="number"], select {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #5cb85c;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #4cae4c;
        }
        .error {
            color: red;
            margin-bottom: 10px;
        }
        .user-list {
            margin-top: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<div class="register-container">
    <h1>注册</h1>
    <form action="register-servlet" method="post">
        <input type="text" name="username" placeholder="用户名" required="required" />
        <input type="password" name="password" placeholder="密码" required="required" />
        <input type="password" name="confirmPassword" placeholder="再次输入密码" required="required" />
        <select name="gender" required="required">
            <option value="">选择性别</option>
            <option value="男">男</option>
            <option value="女">女</option>
            <option value="其他">其他</option>
        </select>
        <input type="number" name="age" placeholder="年龄" required="required" />
        <button type="submit">注册</button>
    </form>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <!-- 显示注册结果 -->
    <div class="user-list">
        <h2>已注册用户</h2>
        <table>
            <thead>
            <tr>
                <th>用户名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>注册时间</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<User> users = (List<User>) request.getAttribute("users");
                if (users != null) {
                    for (User user : users) {
            %>
                <tr>
                    <td><%= user.getUsername() %></td>
                    <td><%= user.getGender() %></td>
                    <td><%= user.getAge() %></td>
                    <td><%= user.getRegistrationTime() %></td>
                </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>