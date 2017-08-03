<%--
  Created by IntelliJ IDEA.
  User: hfismyangel@163.com
  Date: 2017/7/5
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ex" uri="/Task4_page/mytaglib" %>
<html>
<head>
    <title>Title</title>
    <title>Title</title>
    <link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/t11.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <div class="nav-title">首页&gt;职业</div>
    <div class="nav-bar">
        <span class="">方向：</span>
        <a class="nav-bar-a a-selected" href="">全部</a>
        <a class="nav-bar-a" href="">前端开发</a>
        <a class="nav-bar-a" href="">后端开发</a>
        <a class="nav-bar-a" href="">移动开发</a>
        <a class="nav-bar-a" href="">整站开发</a>
        <a class="nav-bar-a" href="">运营维护</a>
    </div>
    <div class="caption">
        <h4>前端开发方向</h4>
    </div>

    <c:forEach items="${develperList}" var="Developer">
    <div class="row" align="center">
        <div class="col-md-4 col-sm-6 col-xs-12 top-margin" align="center">

            <div class="warp-border">
                <div class="clearfix">
                    <div class="icon-people"><img src="${pageContext.request.contextPath}/imges/687.png"></div>
                    <div class="text">
                        <h4 class="">${Developer.type}</h4>
                        <p class="text-present">${Developer.description}"</p>
                    </div>
                </div>

                <div class="warp-class2">
                    <div class="warp-class2-text">
                        <div class="iconfont text-padding">门槛 <img src="${pageContext.request.contextPath}/imges/xx.png"></div>
                    </div>
                    <div class="warp-class2-text">
                        <div class="iconfont text-padding text-border-left">难易程度 <img src="${pageContext.request.contextPath}/imges/xx.png"><img
                                src="${pageContext.request.contextPath}/imges/xx.png"></div>
                    </div>
                </div>
                <div class="warp-class2">
                    <div class="warp-class2-text">
                        <div class="iconfont text-padding">成长周期 <span class="iconfont-color">${Developer.growup}</span>年
                        </div>
                    </div>
                    <div class="warp-class2-text">
                        <div class="iconfont text-padding text-border-left">稀缺程度 <span
                                class="iconfont-color">${Developer.needed}</span>家公司需要
                        </div>
                    </div>
                </div>

                <div class="warp-class2">
                    <div class="leftWarp">
                        薪资待遇
                    </div>
                    <div class="rightWarp">
                        <div class="rightWarp-class">
                            <div class="rightWarp-year">0-1年</div>
                            <div class="rightWarp-wages">${Developer.pay0_1}</div>
                        </div>
                        <div class="rightWarp-class">
                            <div class="rightWarp-year">0-1年</div>
                            <div class="rightWarp-wages">${Developer.pay1_3}</div>
                        </div>
                        <div class="rightWarp-class border-bottom">
                            <div class="rightWarp-year">0-1年</div>
                            <div class="rightWarp-wages">${Developer.pay3_5}</div>
                        </div>
                    </div>
                </div>

                <div class="warp-class2">
                    <b class="text-b">有${Developer.studypeople}人正在学</b>
                </div>
                <div class="warp-class2">
                    <p class="text-p">提示:在你学习之前你应该已经掌握语言基础</p>
                    <p class="text-p">更新时间：<ex:timeTaglib longTimeHandler="${Developer['create_at']}"></ex:timeTaglib>
                           </p>
                </div>

                <div class="flip-container">
                    <p class="flip-title">${Developer.tag}</p>
                    <p class="flip-text">${Developer.introduce}</p>
                </div>
            </div>

        </div>
        </c:forEach>

</body>
</html>
