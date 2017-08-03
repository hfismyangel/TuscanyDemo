<%--
  Created by IntelliJ IDEA.
  User: hfismyangel@163.com
  Date: 2017/7/14
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/../bootstrap-3.3.5-dist/css/bootstrap.css">
    <link href="${pageContext.request.contextPath}/css/Untitled-3.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/Untitled-1base.css" rel="stylesheet" type="text/css">
</head>
<script type="text/javascript">
    $(function () {
        $('#sendMessage').click(
            function () {
                $.ajax({
                    type:"POST",
                    url: "/Task4_page/page/sendmessage",
                    data:{"telephone":$("#telephone").val()},
                    dataType:JSON,
                    success:function (html) {
                        alert("请输入收到的验证码！")
                    }
                })
            }
        );

    });
</script>
<body>
<div align="center">
    <form action="<%=basePath%>page/register" method="post">
        <table>
            <tr>
                <td><h1>用户注册：</h1></td>
            </tr>
            <tr>
                <td>用户名：</td>

                <td><input type="text" name="userName"/></td>

            </tr>
            <tr>
                <td>手机：</td>

                <td><input id="telephone" type="text" name="telephone"/></td>

            </tr>
            <tr>
                <td>邮箱：</td>

                <td><input type="text" name="email"/></td>

            </tr>

            <tr>
                <td>密码：</td>

                <td><input type="text" name="password"/></td>

            </tr>
            <tr>
                <td>短信验证码：</td>

                <td><input type="text" name="telephoneRegister" id="telephoneRegister"/></td>

            </tr>
            <tr>
                <td><input type="button" id="sendMessage" value="获取短信验证码"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="注册"
                />
                    <input type="reset" value="重置"/></td>

            </tr>

        </table>

    </form>
</div>
</body>
</html>
