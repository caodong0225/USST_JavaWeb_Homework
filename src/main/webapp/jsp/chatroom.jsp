<%--
  Created by IntelliJ IDEA.
  User: jyzxc
  Date: 2024/11/1
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN" data-darkreader-mode="dynamic" data-darkreader-scheme="dimmed">

<body>
<div style="text-align:center; margin-top:20%;">
    <h2>欢迎来到聊天室</h2>
    <form action="ChatRoomLoginServlet" method="post">
        <div>
            <label for="username">用户名:</label>
            <input type="text" id="username" name="username">
        </div>
        <!-- 动态加载验证码图片 -->
        <div>
            <label for="code">请输入验证码:</label>
            <br>
            <img src="data:image/jpg;base64,<%= session.getAttribute("base64String") %>" alt="验证码">
            <br>
            <input type="text" name="code" id="code">
        </div>
        <button type="submit">登录</button>

    </form>
    <% // 如果有错误信息，显示错误信息
        if (session.getAttribute("error") != null || session.getAttribute("error") != "") { %>
    <p style="color: crimson"><%= session.getAttribute("error") %></p>
    <% } %>
</div>


</body>
</html>