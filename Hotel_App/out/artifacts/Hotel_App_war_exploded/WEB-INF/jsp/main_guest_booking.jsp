<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="err" uri="/WEB-INF/TLDs/errorTag.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ include file="locale.jsp" %>
<fmt:setBundle var="main_admin" basename="ua.fantotsy.properties.i18n.main_admin"/>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <title>Бронювання</title>
    <link href="../../css/guest.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<header>
<%@include file="guest_header.jsp" %>
</header>
<h1>Доступні апартаменти</h1>
<form action="/guest">
    <input type="submit" name="submit" value="Повернутися" id="back"/>
</form>
<c:choose>
    <c:when test="${not empty requestScope.listOfCategories}">
        <table>
            <tr>
                <th>Тип</th>
                <th>Кількість місць</th>
                <th>Ціна (грн/ніч)</th>
                <th>Кількість номерів</th>
                <th></th>
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
                        <form action="/order_valid" method="get">
                            <input type="hidden" name="category_id" value="${category.categoryId}"/>
                            <select name="booked_apartments[]" multiple>
                                <option value="default" selected disabled>Оберіть номери</option>
                                <c:forEach items="${requestScope.listOfApartments}" var="apartment">
                                    <c:if test="${apartment.value == category.categoryId}">
                                        <option value="${apartment.key}">${apartment.key}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            <input type="submit" name="book_room" value="Забронювати" id="book_room"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        Список порожній!
    </c:otherwise>
</c:choose>
</body>
</html>
