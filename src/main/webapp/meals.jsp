<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.model.MealWithExceed" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019-04-23
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HW Meals</title>
    <style>
        .normal {color: green}
        .exceeded {color:red}
    </style>
</head>
<body>
    <h3></h3><a href="users.jsp">Return to users </a></h3>
    <h2>my meals in this page</h2>
    <h1>
        <%--<%--%>
        <%--Meal meal = null;--%>
        <%--List list = (List)request.getAttribute("lists");--%>
        <%--for (int i = 0; i < list.size(); i++) {--%>
            <%--meal = (Meal)list.get(i);--%>
            <%--out.println(meal.getDescription() +" - "+ meal.getCalories()+ " = "+ meal.getDate());--%>
        <%--}--%>
        <%--%>--%>
            <table border="1">
                <tbody>
                <tr>
                <th>Calories</th>
                <th>Description</th>
                <th>Time</th>
                </tr>

        <c:forEach var="meals" items="${lists}">
                <tr class=${meals.exceed? 'exceeded':'normal'}>
                <td>${meals.calories}</td>
                <td>${meals.description}</td>
                <td>${meals.dateTime}</td>
                </tr>
        </c:forEach>
                </tbody>
            </table>
    </h1>

</body>
</html>
