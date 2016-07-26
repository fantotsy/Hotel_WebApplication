<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="err" uri="/WEB-INF/TLDs/errorTag.tld" %>
<%@ include file="locale.jsp" %>
<fmt:setBundle var="main_admin" basename="ua.fantotsy.properties.i18n.main_admin"/>
<fmt:setBundle var="apartments" basename="ua.fantotsy.properties.i18n.apartments"/>
<html>
    <head>
        <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
        <title><fmt:message key="title" bundle="${apartments}"/></title>
        <link href="../../css/admin.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <header>
            <%@include file="admin_header.jsp" %>
        </header>
        <div>
            <h1><fmt:message key="header" bundle="${apartments}"/></h1>
            <form action="/admin">
                <fmt:message var="back_button" key="back_button" bundle="${apartments}"/>
                <input type="submit" name="submit" value="${back_button}" id="back"/>
            </form>
            <table id="apartments_table">
                <tr>
                    <th><fmt:message key="table_type_column" bundle="${apartments}"/></th>
                    <th><fmt:message key="table_capacity_column" bundle="${apartments}"/></th>
                    <th><fmt:message key="table_price_column" bundle="${apartments}"/></th>
                    <th><fmt:message key="table_apartments_amount_column" bundle="${apartments}"/></th>
                    <th><fmt:message key="table_add_delete_column" bundle="${apartments}"/></th>
                </tr>
                <c:forEach items="${requestScope.listOfCategories}" var="category">
                    <tr>
                        <td>${category.type}</td>
                        <td>${category.numberOfBeds}</td>
                        <td>${category.price}</td>
                        <td>${category.apartments}</td>
                        <td>
                            <c:if test="${requestScope.error[0] == category.categoryId}">
                                <%--The next tag prints error message if it is needed--%>
                                <err:error errorType="${requestScope.error[1]}" locale="${sessionScope.locale}"/>
                            </c:if>
                            <form action="/apartments" method="get">
                                <input type="hidden" name="category_id" value="${category.categoryId}"/>
                                <fmt:message var="apartment_placeholder" key="apartment_placeholder" bundle="${apartments}"/>
                                <input type="text" pattern="[0-9]{3}" name="apartment_number"
                                       placeholder="${apartment_placeholder}" required/>
                                <fmt:message var="add_button" key="add_button" bundle="${apartments}"/>
                                <input type="submit" name="add_apartment" value="${add_button}" id="add_room"/>
                                <fmt:message var="delete_button" key="delete_button" bundle="${apartments}"/>
                                <input type="submit" name="remove_apartment" value="${delete_button}" id="remove_room"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>