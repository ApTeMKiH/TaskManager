<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: PC-6
  Date: 25.04.2017
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tasks</title>
    <meta name="keywords" content="clean blog template, html css layout" />
    <meta name="description" content="Clean Blog Template is provided by templatemo.com" />
    <link href="/resources/blog/templatemo_style.css" rel="stylesheet" type="text/css" />

    <link href="/resources/blog/s3slider.css" rel="stylesheet" type="text/css" />
    <!-- JavaScripts-->
    <script type="text/javascript" src="/resources/blog/js/jquery.js"></script>
</head>
<body>
<div id="templatemo_wrapper">
    <div id="templatemo_left_column">
        <div id="templatemo_header">
            <div id="site_title">
                <h1><a href="/">Task <strong>Manager</strong><span>Hello, ${currentUser.firstName} ${currentUser.lastName}</span></a></h1>
            </div><!-- end of site_title -->
        </div> <!-- end of header -->
    </div>
    <div id="templatemo_menu">
        <ul>
            <li><a href="/" class="current">Tasks</a></li>
            <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                <li><a href="/tasks/add">Add task</a></li>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <li><a href="/sign-in">Sign in</a></li>
                <li><a href="/registration">Sign up</a></li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                <li><a href="/logout">Logout</a></li>
            </sec:authorize>
        </ul>
    </div> <!-- end of templatemo_menu -->
    <div id="templatemo_right_column">
        <div id="templatemo_main">
            <div class="post_section">
            <div id="comment_form">
                <h3>Add task</h3>
                <form action="/tasks/add" method="post">
                    <div class="form_row">
                        <label><strong>Subject</strong> (required)</label>
                        <br />
                        <input type="text" name="subject" required/>
                    </div>
                    <div class="form_row">
                        <label><strong>To whom</strong> (required)</label>
                        <br />
                        <select type="text" name="toWhom" >
                            <c:forEach items="${users}" var="user">
                                <option value="${user.email}">${user.email}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form_row">
                        <label><strong>Text</strong></label>
                        <br />
                        <textarea  name="text" rows="" cols="" required maxlength="1000"></textarea>
                    </div>
                    <input type="submit" name="Submit" value="Submit" class="submit_btn" />
                </form>
            </div>
            </div>
        </div> <!-- end of main -->
        <div class="cleaner"></div>
    </div>
    <!-- end of templatemo_main -->
    <div class="cleaner_h20"></div>
    <div class="cleaner"></div>
</div>
</body>
</html>
