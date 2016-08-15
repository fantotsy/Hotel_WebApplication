<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="err" uri="/WEB-INF/TLDs/errorTag.tld" %>
<%@ include file="locale.jsp" %>
<fmt:setBundle var="main_guest" basename="ua.fantotsy.properties.i18n.main_guest"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title><fmt:message key="title" bundle="${main_guest}"/></title>
        <link href="../../css/guest.css" type="text/css" rel="stylesheet"/>
        <link href="../../css/header.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <div id="main_wrapper">
            <header>
                <%@include file="guest_header.jsp" %>
            </header>
            <div id="wrapper">
                <div id="order_form">
                    <h2><fmt:message key="order_header" bundle="${main_guest}"/></h2>
                    <%--The next tag prints error message if it is needed--%>
                    <err:error errorType="${requestScope.error}" locale="${sessionScope.locale}"/>
                    <form action="/booking" method="get" class="clearfix">
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
                                <input type="date" name="check-in_date" max="${requestScope.yearLater}"
                                       min="${requestScope.today}" required/>
                            </div>
                            <div id="departure_date">
                                <fmt:message key="departure_date_header" bundle="${main_guest}"/>
                                <br/>
                                <input type="date" name="check-out_date" max="${requestScope.yearLater}"
                                       min="${requestScope.today}" required/>
                            </div>
                        </div>
                        <fmt:message var="search_button" key="search_button" bundle="${main_guest}"/>
                        <button type="submit" name="search" id="search">${search_button}</button>
                    </form>
                </div>
                <div id="reservations_table">
                    <h1 id="table_title"><fmt:message key="reservations_table_header" bundle="${main_guest}"/></h1>
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
                                            <form action="/guest" method="post">
                                                <input type="hidden" name="anti_csrf_token"
                                                       value="${requestScope.antiCsrfToken}"/>
                                                <input type="hidden" name="reservation_id"
                                                       value="${reservation.reservationId}"/>
                                                <fmt:message var="cancel_button" key="cancel_button"
                                                             bundle="${main_guest}"/>
                                                <button type="submit" name="cancel_reservation"
                                                        id="cancel_reservation">${cancel_button}</button>
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
        </div>
    </body>
</html>