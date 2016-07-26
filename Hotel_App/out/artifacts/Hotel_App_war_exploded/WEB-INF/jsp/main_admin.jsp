<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ include file="locale.jsp" %>
<fmt:setBundle var="main_admin" basename="ua.fantotsy.properties.i18n.main_admin"/>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <title><fmt:message key="title" bundle="${main_admin}"/></title>
    <link href="../../css/admin.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<header>
    <%@include file="admin_header.jsp" %>
</header>
<div id="actions">
    <form action="/apartments">
        <fmt:message var="apartments_button" key="apartments_button" bundle="${main_admin}"/>
        <input type="submit" name="delete-insert" value="${apartments_button}"/>
    </form>
    <form action="/reservations">
        <fmt:message var="reservations_button" key="reservations_button" bundle="${main_admin}"/>
        <input type="submit" name="get-all-reservations" value="${reservations_button}"/>
    </form>
    <form action="/guests">
        <fmt:message var="guests_button" key="guests_button" bundle="${main_admin}"/>
        <input type="submit" name="get-guests" value="${guests_button}"/>
    </form>
</div>
</body>
</html>
