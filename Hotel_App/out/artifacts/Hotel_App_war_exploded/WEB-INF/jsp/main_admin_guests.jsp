<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <title>Користувачі</title>
    <link href="../../css/admin.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<header>
    <%@include file="admin_header.jsp" %>
</header>
<div>
    <h1>Таблиця гостей</h1>
    <form action="/admin">
        <input type="submit" name="submit" value="Повернутися" id="back"/>
    </form>

    <table id="guests_table">
        <tr>
            <th>Ім'я</th>
            <th>Прізвище</th>
            <th>Логін</th>
            <th>Номер телефону</th>
            <th>Електронна пошта</th>
        </tr>
        <c:forEach items="${requestScope.listOfGuests}" var="guest">
            <tr>
                <td>${guest.name}</td>
                <td>${guest.lastName}</td>
                <td>${guest.login}</td>
                <td>${guest.phoneNumber}</td>
                <td>${guest.email}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>