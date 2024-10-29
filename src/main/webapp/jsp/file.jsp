<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.File" %>
<%@ page import="com.web.javaweb.utils.FileUtils" %><%--
  Created by IntelliJ IDEA.
  User: jyzxc
  Date: 2024/10/29
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件下载</title>
</head>
<body>
<h2>可下载的文件列表:</h2>
<%
    // 获取 download 目录的绝对路径
    String path = request.getServletContext().getRealPath("/download/");
    File file = new File(path);
    File[] files = file.listFiles();

    // 检查文件是否存在
    if (files != null && files.length > 0) {
        for (File f : files) {
            String fileName = f.getName();
            // 设置文件的sha256值，设置文件防盗链
            String sha256 = null;
            try {
                sha256 = FileUtils.calculateSHA256(f);
            } catch (Exception ignored) {

            }
%>
<!-- 输出文件名和下载链接 -->
<a href="download?filename=<%=fileName%>&sha256=<%=sha256%>"><%=fileName%></a><br>
<%
    }
} else {
%>
<p>没有可下载的文件。</p>
<%
    }
%>
</body>
</html>
