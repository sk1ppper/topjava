<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Users</h2>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>ID</th>
        <th>Имя</th>
        <th>Почтовый Ящик</th>
        <th>Роли</th>
    </tr>
    </thead>
    <c:forEach items="${users}" var="user">
<%--        <jsp:useBean id="user" scope="page" class="ru.javawebinar.topjava.model.User"/>--%>
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.roles}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>