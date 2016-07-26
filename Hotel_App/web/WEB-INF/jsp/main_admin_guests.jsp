<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ include file="locale.jsp" %>
<fmt:setBundle var="main_admin" basename="ua.fantotsy.properties.i18n.main_admin"/>
<fmt:setBundle var="guests" basename="ua.fantotsy.properties.i18n.guests"/>
<html>
    <head>
        <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
        <title><fmt:message key="title" bundle="${guests}"/></title>
        <link href="../../css/admin.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <header>
            <%@include file="admin_header.jsp" %>
        </header>
        <div>
            <h1><fmt:message key="header" bundle="${guests}"/></h1>
            <form action="/admin">
                <fmt:message var="back_button" key="back_button" bundle="${guests}"/>
                <input type="submit" name="submit" value="${back_button}" id="back"/>
            </form>
            <table id="guests_table">
                <tr>
                    <th><fmt:message key="table_name_column" bundle="${guests}"/></th>
                    <th><fmt:message key="table_surname_column" bundle="${guests}"/></th>
                    <th><fmt:message key="table_login_column" bundle="${guests}"/></th>
                    <th><fmt:message key="table_phone_column" bundle="${guests}"/></th>
                    <th><fmt:message key="table_email_column" bundle="${guests}"/></th>
                </tr>
                <c:forEach items="${requestScope.listOfGuests}" var="guest">
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