<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ include file="locale.jsp" %>
<fmt:setBundle var="error" basename="ua.fantotsy.properties.i18n.error"/>
<html>
    <head>
        <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
        <title><fmt:message key="title" bundle="${error}"/></title>
        <link href="../../css/error.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <h1><fmt:message key="header" bundle="${error}"/></h1>
        <div id="error_details">
            <h3><fmt:message key="advice_header" bundle="${error}"/></h3>
            <ul>
                <li><fmt:message key="first_advice" bundle="${error}"/></li>
                <li><fmt:message key="second_advice" bundle="${error}"/></li>
            </ul>
            <form action="/index" method="post">
                <fmt:message var="to_start_page_button" key="to_start_page_button" bundle="${error}"/>
                <input type="submit" name="index" value="${to_start_page_button}" class="button"/>
            </form>
            <button onclick='history.back()' class="button"><fmt:message key="to_previous_page_button"
                                                                         bundle="${error}"/></button>
        </div>
    </body>
</html>