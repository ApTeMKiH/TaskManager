<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Артем
  Date: 4/29/2017
  Time: 12:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Feedback</title>
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
                <li><a href="/registration">Sing up</a></li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                <li><a href="/logout">Logout</a></li>
            </sec:authorize>
        </ul>
    </div> <!-- end of templatemo_menu -->
    <div id="templatemo_right_column">
        <div id="templatemo_main">
            <div id="comment_form">
                <h3>Leave a comment</h3>
                <form action="/tasks/feedback/edit" method="post">
                    <input name="feedbackId" value="${feedback.id}" hidden/>
                    <div class="form_row">
                        <label><strong>Mark</strong> (required)</label>
                        <br />
                        <input type="text" name="mark" value="${feedback.mark}" required/>
                    </div>
                    <div class="form_row">
                        <label><strong>Text</strong></label>
                        <br />
                        <textarea  name="text" rows="" cols=""  required>${feedback.text}</textarea>
                    </div>
                    <input type="submit" name="Submit" value="Submit" class="submit_btn" />
                </form>
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
