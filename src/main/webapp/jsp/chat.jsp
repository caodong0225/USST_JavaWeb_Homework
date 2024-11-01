<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: jyzxc
  Date: 2024/11/1
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN" data-darkreader-mode="dynamic" data-darkreader-scheme="dimmed">
<body>
<div class="container">

    <h2>当前用户:</h2>
    <h3 id="currentUser">
        <%
            // 获取当前用户
            String userName = (String) session.getAttribute("userName");
            if (userName == null) {
                request.getRequestDispatcher("/jsp/chatroom.jsp").forward(request, response);
            }
        %>
        <%=userName%>
    </h3>
    <!-- 聊天内容区 -->
    <div id="chatContent">

    </div>

    <!-- 操作区 -->
    <div id="chatOperation">
        <form id="chatForm" method="post" action="chat">
            <!-- 用户列表 -->
            <div>
                <label for="userList">发送至：</label>
                <select name="to" id="userList">
                    <option value="所有人">所有人</option>
                    <%
                        Set<String> userNames = (Set<String>) application.getAttribute("userNames");
                        for (String user : userNames) {
                    %>
                    <option value="<%=user%>"><%=user%></option>
                    <%
                        }
                    %>
                    }
                </select>
            </div>
            <!-- 输入和发送消息 -->
            <div>
                <input type="text" name="content" id="messageInput" placeholder="输入聊天内容">
                <input type="hidden" name="from" value=<%=userName%>>
                <button type="submit" id="sendButton">发送</button>
            </div>
        </form>
    </div>

    <!-- 在线用户列表 -->
    <h4>在线用户</h4>
    <ul id="onlineUsersList">
        <%
            for (String user : userNames) {
        %>
        <li><%=user%></li>
        <%
            }
        %>
    </ul>

    <form action="logout" method="get">
        <p name="username">当前用户：
            <%=userName%>
        </p>
        <input type="submit" value="退出">
    </form>
</div>

<script>
    let lastActiveTimeFromServer = new Date(); // 从服务器获取的最后活跃时间


    function checkAndLogoutIfInactive() {
        // console.log("最后活跃时间:" + lastActiveTimeFromServer);
        let now = new Date();
        // console.log("当前时间:" + now);
        let differenceInSeconds = (now - lastActiveTimeFromServer) / 1000;
        // console.log("差值:" + differenceInSeconds);
        // 如果超过一小时没有活跃，就自动退出
        //console.log(differenceInSeconds);
        if (differenceInSeconds > 3600) {
            // 自动重定向到/logout进行退出，方法设置为post
            window.location.href = 'logout';

        }
    }

    setInterval(checkAndLogoutIfInactive, 5000); // 每5秒检查一次

    let username = document.querySelector('#currentUser').innerText; // 当前用户
    // console.log("当前用户:" + username);

    function fetchChatData() {
        fetch('chat')
            .then(response => {
                console.log(response.toString());
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // console.log(data);
                const onlineUsersList = document.querySelector('#onlineUsersList');
                onlineUsersList.innerHTML = ''; // 清空现有的内容
                data.userList.forEach(user => {
                    console.log('用户为' + user);
                    onlineUsersList.innerHTML += '<li>' + user + '</li>';
                });
                const userList = document.querySelector('#userList');
                const selectedValue = userList.value;
                // 获取当前下拉框选择的对象
                userList.innerHTML = '<option value="所有人">所有人</option>'; // 清空现有的内容
                data.userList.forEach(user => {
                    // 如果和当前选中的用户相同，那么设置selected
                    if(selectedValue === user)
                    {
                        userList.innerHTML += '<option value="' + user + '" selected>' + user + '</option>';
                    }
                    else
                    {
                        userList.innerHTML += '<option value="' + user + '">' + user + '</option>';
                    }
                });
                const chatContent = document.querySelector('#chatContent');
                chatContent.innerHTML = ''; // 清空现有的内容
                data.messageList.forEach(msg => {
                    console.log('消息为' + msg.from + msg.to + msg.content);
                    // 判断：如果msg.to是所有人，或者msg.to是当前用户，或者msg.from是当前用户，就显示
                    if (msg.to === '所有人' || msg.to === username || msg.from === username) {
                        chatContent.innerHTML += '<p>' + msg.from + '对' + msg.to + '说：' + msg.content + '</p>';
                    }

                });
            })
            .catch(error => {
                console.log('Fetch error: ', error);
            });
    }

    // 定时刷新聊天内容 - 5秒
    setInterval(fetchChatData, 5000);
    // 页面加载完成后，立即刷新聊天内容
    fetchChatData();

</script>


</body>
</html>
