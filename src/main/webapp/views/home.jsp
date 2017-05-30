<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: PC-6
  Date: 25.04.2017
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task manager</title>
    <meta name="keywords" content="clean blog template, html css layout" />
    <meta name="description" content="Clean Blog Template is provided by templatemo.com" />
    <link href="/resources/blog/templatemo_style.css" rel="stylesheet" type="text/css" />
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

    <link href="/resources/blog/s3slider.css" rel="stylesheet" type="text/css" />
    <!-- JavaScripts-->
    <script type="text/javascript" src="/resources/blog/js/jquery.js"></script>
</head>
<body>
<div id="templatemo_wrapper">
    <div id="templatemo_left_column">

        <div id="templatemo_header">

            <div id="site_title">
                <h1><a href="/" >Task <strong>Manager</strong><span>Hello, ${currentUser.firstName} ${currentUser.lastName}</span></a></h1>
            </div><!-- end of site_title -->

        </div> <!-- end of header -->
    </div>
        <div id="templatemo_menu">
        <ul>
            <li><a href="/" class="current">Tasks</a></li>
                <li><a href="/tasks/add">Add task</a></li>
                <li><a href="/tasks/own">From you</a></li>
                <li><a href="/logout">Logout</a></li>
        </ul>
    </div> <!--id="templatemo_right_column" end of templatemo_menu -->
    <div >
        <div id="templatemo_main" data-size="${tasks.size()}">
            <c:if test="${tasks.size() == 0}">
                <div class="post_section">
                    <h2>You don't have any tasks.</h2>
                </div>
            </c:if>
            <c:if test="${tasks.size() != 0}">
            <c:forEach items="${tasks}" var="tasks">
            <div class="post_section">
                <h2><a href="tasks/task/${tasks.id}">${tasks.subject}</a></h2>
                ${tasks.date} <strong>Author:</strong> ${tasks.user.lastName}<span>| Feedbacks: ${tasks.feedbackList.size()}</span>
                <pre style="overflow-x:hidden;">${tasks.text}</pre>
            </div>
            </c:forEach>
            </c:if>
        </div>
        <div class="cleaner"></div>
    </div>
    <!-- end of templatemo_main -->
    <div class="cleaner_h20"></div>
    <div class="cleaner"></div>
</div> <!-- end of warpper -->
<script src="/resources/myScript.js"></script>
</body>
</html>
