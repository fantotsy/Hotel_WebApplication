<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="err" uri="/WEB-INF/TLDs/errorTag.tld" %>
<%@ include file="locale.jsp" %>
<fmt:setBundle var="main_guest" basename="ua.fantotsy.properties.i18n.main_guest"/>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <title><fmt:message key="title" bundle="${main_guest}"/></title>
    <link href="../../css/guest.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<header>
    <%@include file="guest_header.jsp" %>
</header>
<div id="wrapper">
    <div id="order_form">
        <h2><fmt:message key="order_header" bundle="${main_guest}"/></h2>
        <%--The next tag prints error message if it is needed--%>
        <err:error errorType="${requestScope.error}" locale="${sessionScope.locale}"/>
        <form action="/booking" method="get">
            <input type="hidden" name="date_chosen" value="true"/>
            <select name="apartment_type[]" multiple id="type_selector">
                <option value="default" disabled selected><fmt:message key="default_type_option"
                                                                       bundle="${main_guest}"/></option>
                <c:forEach items="${requestScope.listOfTypes}" var="type">
                    <option value="${type}">${type}</option>
                </c:forEach>
            </select>
            <select name="apartment_capacity[]" multiple id="capacity_selector">
                <option value="default" disabled selected><fmt:message key="default_capacity_option"
                                                                       bundle="${main_guest}"/></option>
                <c:forEach items="${requestScope.listOfCapacities}" var="capacity">
                    <option value="${capacity}">${capacity}</option>
                </c:forEach>
            </select>
            <br/>
            <div id="dates">
                <div id="arrival_date">
                    <fmt:message key="arrival_date_header" bundle="${main_guest}"/>
                    <br/>
                    <input type="date" name="check-in_date" max="${requestScope.yearLater}" min="${requestScope.today}"
                           required/>
                </div>
                <div id="departure_date">
                    <fmt:message key="departure_date_header" bundle="${main_guest}"/>
                    <br/>
                    <input type="date" name="check-out_date" max="${requestScope.yearLater}" min="${requestScope.today}"
                           required/>
                </div>
            </div>
            <br/>
            <fmt:message var="search_button" key="search_button" bundle="${main_guest}"/>
            <input type="submit" name="search" value="${search_button}" id="search"/>
        </form>
    </div>
    <div id="reservations_table">
        <h2><fmt:message key="reservations_table_header" bundle="${main_guest}"/></h2>
        <c:choose>
            <c:when test="${not empty requestScope.listOfReservations}">
                <table>
                    <tr>
                        <th><fmt:message key="table_apartment_column" bundle="${main_guest}"/></th>
                        <th><fmt:message key="table_type_column" bundle="${main_guest}"/></th>
                        <th><fmt:message key="table_capacity_column" bundle="${main_guest}"/></th>
                        <th><fmt:message key="table_arrival_column" bundle="${main_guest}"/></th>
                        <th><fmt:message key="table_departure_column" bundle="${main_guest}"/></th>
                        <th><fmt:message key="table_total_price_column" bundle="${main_guest}"/></th>
                        <th></th>
                    </tr>
                    <c:forEach items="${requestScope.listOfReservations}" var="reservation">
                        <tr>
                            <td>${reservation.apartment.apartmentId}</td>
                            <td>${reservation.apartment.category.type}</td>
                            <td>${reservation.apartment.category.numberOfBeds}</td>
                            <td>${reservation.arrival}</td>
                            <td>${reservation.departure}</td>
                            <td>${reservation.totalPrice}</td>
                            <td>
                                <form action="/guest" method="get">
                                    <input type="hidden" name="reservation_id" value="${reservation.reservationId}"/>
                                    <fmt:message var="cancel_button" key="cancel_button" bundle="${main_guest}"/>
                                    <input type="submit" name="cancel_reservation" value="${cancel_button}"
                                           id="cancel_reservation"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <h2><fmt:message key="empty_table_title" bundle="${main_guest}"/></h2>
                <h3><fmt:message key="empty_table_info" bundle="${main_guest}"/></h3>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>