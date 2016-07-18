<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Реєстраційна форма</title>
        <link href="css/registration.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <c:choose>
            <c:when test="${result == 'guest inserted'}">
                <%@include file="registration_confirmation.jsp"%>
            </c:when>
            <c:otherwise>
                <%@include file="registration_form.jsp"%>
            </c:otherwise>
        </c:choose>
    </body>
</html>