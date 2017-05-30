<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Артем
  Date: 4/26/2017
  Time: 6:41 PM
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
    <div >
        <div id="templatemo_main">
            <div class="post_section">
                <h2>${task.subject}</h2>
                ${task.date} | <strong>Author:</strong> ${task.user.firstName}
                <p style="overflow-x:hidden;">${task.text}</p>

                <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
                    <c:choose>
                    <c:when test="${user.id == task.user.id}">
                    <div class="submit_btn"><a href="/tasks/edit/${task.id}">Edit</a></div>
                    <div class="submit_btn"><a href="/tasks/delete/${task.id}">Delete</a></div>
                    </c:when>
                    </c:choose>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <div class="submit_btn"><a href="/tasks/edit/${task.id}">Edit</a></div>
                            <div class="submit_btn"><a href="/tasks/delete/${task.id}">Delete</a></div>
                </sec:authorize>

            </div>
            <div class="comment_tab">
                Comments           </div>
            <div id="comment_section" >
                <c:forEach items="${feedbacks}" var="feedback">
                <ol class="comments first_level">
                    <li>
                        <div class="comment_box commentbox1">
                            <div class="comment_text">
                                <div class="comment_author">${feedback.user.lastName}<span class="date">${feedback.date}</span><span class="time">Mark: ${feedback.mark}</span></div>
                                <p style="overflow-x:hidden;">${feedback.text}</p>
                                <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
                                    <c:choose>
                                        <c:when test="${user.id == task.user.id || user.id == feedback.user.id}">
                                            <div class="submit_btn"><a href="/tasks/feedback/edit/${feedback.id}">Edit</a></div>
                                            <div class="submit_btn"><a href="/tasks/feedback/delete/${feedback.id}">Delete</a></div>
                                        </c:when>
                                    </c:choose>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <div class="submit_btn"><a href="/tasks/feedback/edit/${feedback.id}">Edit</a></div>
                                    <div class="submit_btn"><a href="/tasks/feedback/delete/${feedback.id}">Delete</a></div>
                                </sec:authorize>
                            </div>
                            <div class="cleaner"></div>
                        </div>
                    </li>
                </ol>
                </c:forEach>
            </div>
            <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
            <div id="comment_form">
                <h3>Leave a comment</h3>
                <form action="/tasks/task" method="post">
                    <input name="artId" value="${task.id}" hidden/>
                    <div class="form_row">
                        <label><strong>Mark</strong> (required)</label>
                        <br />
                        <input type="text" name="mark" required/>
                    </div>
                    <div class="form_row">
                        <label><strong>Text</strong></label>
                        <br />
                        <textarea  name="text" rows="" cols="" required></textarea>
                    </div>
                    <input type="submit" name="Submit" value="Submit" class="submit_btn" />
                </form>
            </div>
            </sec:authorize>
        </div> <!-- end of main -->
        <div class="cleaner"></div>
    </div>
    <!-- end of templatemo_main -->
    <div class="cleaner_h20"></div>
        <div class="cleaner"></div>
    </div>
    <!-- end of templatemo_main -->
<script src="/resources/blog/js/jquery-1.11.1.min.js"></script>
<script src="/resources/blog/js/myScript.js"></script>
</body>
</html>
