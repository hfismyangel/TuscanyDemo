<%--
  Created by IntelliJ IDEA.
  User: hfismyangel@163.com
  Date: 2017/7/13
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>注册成功！</title>
    <a href="<%=basePath%>page/u/homepage">返回主页</a>
</head>
<body>
<h1>注册成功！</h1>
</body>
</html>
