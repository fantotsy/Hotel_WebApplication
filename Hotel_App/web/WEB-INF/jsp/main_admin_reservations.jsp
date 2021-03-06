<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="err" uri="/WEB-INF/TLDs/errorTag.tld" %>
<%@ include file="locale.jsp" %>
<fmt:setBundle var="main_admin" basename="ua.fantotsy.properties.i18n.main_admin"/>
<fmt:setBundle var="reservations" basename="ua.fantotsy.properties.i18n.reservations"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
                <form action="/admin" method="get">
                    <fmt:message var="back_button" key="back_button" bundle="${reservations}"/>
                    <button type="submit" name="back_button" id="back">${back_button}</button>
                </form>
                <table id="info_table">
                    <tr>
                        <th><fmt:message key="table_apartment_column" bundle="${reservations}"/></th>
                        <th><fmt:message key="table_arrival_column" bundle="${reservations}"/></th>
                        <th><fmt:message key="table_departure_column" bundle="${reservations}"/></th>
                        <th><fmt:message key="table_name_column" bundle="${reservations}"/></th>
                        <th><fmt:message key="table_surname_column" bundle="${reservations}"/></th>
                        <th><fmt:message key="table_login_column" bundle="${reservations}"/></th>
                        <th></th>
                    </tr>
                    <c:forEach items="${requestScope.listOfReservations}" var="reservation">
                        <tr>
                            <td>${reservation.apartment.apartmentId}</td>
                            <td>${reservation.arrival}</td>
                            <td>${reservation.departure}</td>
                            <td>${reservation.guest.name}</td>
                            <td>${reservation.guest.lastName}</td>
                            <td>${reservation.guest.login}</td>
                            <td>
                                <c:if test="${requestScope.error[0] == reservation.reservationId}">
                                    <%--The next tag prints error message if it is needed--%>
                                    <err:error errorType="${requestScope.error[1]}"
                                               locale="${sessionScope.locale}"/>
                                </c:if>
                                <form action="/reservations" method="post">
                                    <input type="hidden" name="anti_csrf_token"
                                           value="${sessionScope.antiCsrfToken}"/>
                                    <input type="hidden" name="reservation_id" value="${reservation.reservationId}"/>
                                    <input type="hidden" name="apartment_id" value="${reservation.apartment.apartmentId}"/>
                                    <div id="dates">
                                        <div id="arrival_date">
                                            <br/>
                                            <input type="date" name="check-in_date" max="${requestScope.yearLater}"
                                                   min="${requestScope.today}" required/>
                                        </div>
                                        <div id="departure_date">
                                            <br/>
                                            <input type="date" name="check-out_date" max="${requestScope.yearLater}"
                                                   min="${requestScope.today}" required/>
                                        </div>
                                    </div>
                                    <button type="submit" name="add_apartment" id="add_room">Update</button>
                                </form>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>