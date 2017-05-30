<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: PC-6
  Date: 25.04.2017
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <li><a href="/tasks/all"><span>All tasks</span></a></li>
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
        <c:forEach items="${tasks}" var="tasks">
            <div class="panel panel-primary-head col-md-4" style="padding: 20px;">
                <div class="panel-heading" style="margin-bottom: 20px;">
                    <h4 class="panel-title">${tasks.subject}</h4>
                    <p class="panel-title">${tasks.text}</p>
                </div><!-- panel-heading -->
                <%--<a href="/products/delete/${tasks.id}" class="btn btn-danger">Delete</a>--%>
                <%--<a href="/products/edit/${tasks.id}" class="btn btn-warning">Edit</a>--%>
            </div>
        </c:forEach>
</body>
</html>
