<%--
  Created by IntelliJ IDEA.
  User: jyzxc
  Date: 2024/10/29
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>验证码界面</title>
</head>
<body>
<img src="data:image/jpg;base64,<%= session.getAttribute("base64String") %>" alt="验证码">
<form action="code" method="post">
    <input type="text" name="code">
    <input type="submit" value="提交">
</form>
<% // 如果有错误信息，显示错误信息
    if (session.getAttribute("error") != null || session.getAttribute("error") != "") { %>
    <p style="color: crimson"><%= session.getAttribute("error") %></p>
<% } %>
</body>
</html>
