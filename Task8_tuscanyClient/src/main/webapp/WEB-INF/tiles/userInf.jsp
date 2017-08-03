<%--
  Created by IntelliJ IDEA.
  User: hfismyangel@163.com
  Date: 2017/7/14
  Time: 16:36
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
    <title>Title</title>
</head>
<body>
<form action="<%=basePath%>page/u/doupload">
    <table>
        <tr>
            <td>头像上传路径：</td>
            <td><input type="text" name="photoPath"></td>
        </tr>
        <tr>
            <td><input type="submit" value="开始上传"></td>
        </tr>
    </table>
</form>
</body>
</html>
