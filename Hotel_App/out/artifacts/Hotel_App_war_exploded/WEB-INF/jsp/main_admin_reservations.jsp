<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ include file="locale.jsp" %>
<fmt:setBundle var="main_admin" basename="ua.fantotsy.properties.i18n.main_admin"/>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <title>Резервації</title>
    <link href="../../css/admin.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<header>
    <%@include file="admin_header.jsp" %>
</header>

<div>
    <h1>Таблиця резервацій</h1>
    <form action="/admin">
        <input type="submit" name="submit" value="Повернутися" id="back"/>
    </form>

    <table id="reservations_table">
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