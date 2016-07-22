<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <title>Резервації</title>
    <link href="css/main.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<%@include file="admin_extended_header.jsp" %>
<div id="body">
    <table>
        <tr>
            <th>Номер</th>
            <th>Заселення</th>
            <th>Виселення</th>
            <th>Ім'я</th>
            <th>Прізвище</th>
            <th>Логін</th>
        </tr>
        <c:forEach items="${requestScope.listOfReservations}" var="reservation">
            <tr>
                <td>${reservation.apartment.apartmentId}</td>
                <td>${reservation.arrival}</td>
                <td>${reservation.departure}</td>
                <td>${reservation.guest.name}</td>
                <td>${reservation.guest.lastName}</td>
                <td>${reservation.guest.login}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>