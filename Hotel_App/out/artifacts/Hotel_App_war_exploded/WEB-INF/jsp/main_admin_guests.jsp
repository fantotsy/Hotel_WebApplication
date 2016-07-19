<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <title>Користувачі</title>
    <link href="css/main.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<%@include file="admin_extended_header.jsp" %>
<div id="body">
    <table>
        <tr>
            <th>Ім'я</th>
            <th>Прізвище</th>
            <th>Логін</th>
            <th>Номер телефону</th>
            <th>Електронна пошта</th>
        </tr>
        <c:forEach items="${listOfGuests}" var="guest">
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