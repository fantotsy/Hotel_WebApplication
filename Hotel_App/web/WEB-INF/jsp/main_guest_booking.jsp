<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="err" uri="/WEB-INF/TLDs/errorTag.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ include file="locale.jsp" %>
<fmt:setBundle var="main_guest" basename="ua.fantotsy.properties.i18n.main_guest"/>
<fmt:setBundle var="booking" basename="ua.fantotsy.properties.i18n.booking"/>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <title><fmt:message key="title" bundle="${booking}"/></title>
    <link href="../../css/guest.css" type="text/css" rel="stylesheet"/>
    <link href="../../css/header.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div id="main_wrapper">
    <header>
        <%@include file="guest_header.jsp" %>
    </header>
    <h1><fmt:message key="header" bundle="${booking}"/></h1>
    <div id="booking_wrapper">
        <div id="back_button">
            <form action="/guest" method="post">
                <fmt:message var="back_button" key="back_button" bundle="${booking}"/>
                <button type="submit" name="submit" id="back">${back_button}</button>
            </form>
        </div>
        <c:choose>
            <c:when test="${not empty requestScope.listOfCategories}">
                <table id="booking_table">
                    <tr>
                        <th><fmt:message key="table_type_column" bundle="${booking}"/></th>
                        <th><fmt:message key="table_capacity_column" bundle="${booking}"/></th>
                        <th><fmt:message key="table_price_column" bundle="${booking}"/></th>
                        <th><fmt:message key="table_apartments_amount_column" bundle="${booking}"/></th>
                        <th id="booking_form"></th>
                    </tr>
                    <c:forEach items="${requestScope.listOfCategories}" var="category">
                        <tr>
                            <td>${category.type}</td>
                            <td>${category.numberOfBeds}</td>
                            <td>${category.price}</td>
                            <td>${category.apartments}</td>
                            <td>
                                <c:if test="${requestScope.category_id == category.categoryId}">
                                    <%--The next tag prints error message if it is needed--%>
                                    <err:error errorType="${requestScope.error}" locale="${sessionScope.locale}"/>
                                </c:if>
                                <form action="/order_valid" method="post">
                                    <input type="hidden" name="category_id" value="${category.categoryId}"/>
                                    <select name="booked_apartments[]" multiple>
                                        <option value="default" selected disabled><fmt:message
                                                key="default_apartments_option"
                                                bundle="${booking}"/></option>
                                        <c:forEach items="${requestScope.listOfApartments}" var="apartment">
                                            <c:if test="${apartment.value == category.categoryId}">
                                                <option value="${apartment.key}">${apartment.key}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <fmt:message var="book_button" key="book_button" bundle="${booking}"/>
                                    <input type="submit" name="book_room" value="${book_button}" id="book_room"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <fmt:message key="empty_table" bundle="${booking}"/>
                <br/>
                <fmt:message key="empty_table_advice" bundle="${booking}"/>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>