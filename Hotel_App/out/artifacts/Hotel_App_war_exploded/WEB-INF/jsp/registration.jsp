<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ include file="locale.jsp" %>
<fmt:setBundle var="registration" basename="ua.fantotsy.properties.i18n.registration"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title><fmt:message key="title" bundle="${registration}"/></title>
        <link href="../../css/registration.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <c:choose>
            <c:when test="${requestScope.result == 'guest inserted'}">
                <%@include file="registration_confirmation.jsp"%>
            </c:when>
            <c:otherwise>
                <%@include file="registration_form.jsp"%>
            </c:otherwise>
        </c:choose>
    </body>
</html>