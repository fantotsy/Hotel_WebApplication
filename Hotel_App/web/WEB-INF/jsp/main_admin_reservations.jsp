<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ include file="locale.jsp" %>
<fmt:setBundle var="main_admin" basename="ua.fantotsy.properties.i18n.main_admin"/>
<fmt:setBundle var="reservations" basename="ua.fantotsy.properties.i18n.reservations"/>
<html>
    <head>
        <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
        <title><fmt:message key="title" bundle="${reservations}"/></title>
        <link href="../../css/admin.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <div id="main_wrapper">
            <header>
                <%@include file="admin_header.jsp" %>
            </header>
            <h1><fmt:message key="header" bundle="${reservations}"/></h1>
            <div id="table_wrapper">
                <form action="/admin" method="post">
                    <fmt:message var="back_button" key="back_button" bundle="${reservations}"/>
                    <input type="submit" name="submit" value="${back_button}" id="back"/>
                </form>
                <table id="info_table">
                    <tr>
                        <th><fmt:message key="table_name_column" bundle="${reservations}"/></th>
                        <th><fmt:message key="table_arrival_column" bundle="${reservations}"/></th>
                        <th><fmt:message key="table_departure_column" bundle="${reservations}"/></th>
                        <th><fmt:message key="table_name_column" bundle="${reservations}"/></th>
                        <th><fmt:message key="table_surname_column" bundle="${reservations}"/></th>
                        <th><fmt:message key="table_login_column" bundle="${reservations}"/></th>
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
        </div>
    </body>
</html>