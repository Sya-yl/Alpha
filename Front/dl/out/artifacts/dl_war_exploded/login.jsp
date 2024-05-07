<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/5/7
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<div style="text-align: center">
 <form action="/login" method="post" id="loginForm">
     姓名:<input type="test" name="uname"> <br>
     密码:<input type="password" name="upwd"> <br>
     <span id="msg" style="font-size: 50px;color: red"></span><br>
     <button type="button">登录</button>
     <button type="button">注册</button>
 </form>
</div>
</body>
</html>
