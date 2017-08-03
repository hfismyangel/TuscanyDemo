<%--
  Created by IntelliJ IDEA.
  User: hfismyangel@163.com
  Date: 2017/7/6
  Time: 19:43
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
<div align="center">
    <form action="<%=basePath%>page/u/homepage" method="post">
        <table>
            <tr>
                <td><h1>JNSHU 用户登录：</h1></td>
            </tr>
            <tr>
                <td>用户名：</td>

                <td><input type="text" name="userName"/></td>

            </tr>

            <tr>
                <td>密码：</td>

                <td><input type="password" name="password"/></td>

            </tr>

            <tr>
                <td><a href="registerPage">用户注册</a></td>
                <td colspan="2"><input name="isUseCookies" type="checkbox"/>30天内自动登录</td>

            </tr>

            <tr>
                <td></td>

                <td colspan="2" align="center"><input type="submit" value="登录"
                />
                    <input type="reset" value="重置"/></td>

            </tr>

        </table>

    </form>
</div>
</body>
</html>
