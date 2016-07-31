<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ include file="locale.jsp" %>
<fmt:setBundle var="error" basename="ua.fantotsy.properties.i18n.error"/>
<html>
    <head>
        <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
        <title><fmt:message key="title" bundle="${error}"/></title>
        <link href="../../css/error.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <div id="wrapper">
            <h1><fmt:message key="header" bundle="${error}"/></h1>
            <div id="error_details">
                <h3><fmt:message key="advice_header" bundle="${error}"/></h3>
                <ul>
                    <li><fmt:message key="first_advice" bundle="${error}"/></li>
                    <li><fmt:message key="second_advice" bundle="${error}"/></li>
                </ul>
                <form action="/index" method="post">
                    <fmt:message var="to_start_page_button" key="to_start_page_button" bundle="${error}"/>
                    <button type="submit" name="index" class="button">${to_start_page_button}</button>
                </form>
                <button onclick='history.back()' class="button"><fmt:message key="to_previous_page_button"
                                                                             bundle="${error}"/></button>
            </div>
            <div id="image">
                <img src="../../images/sad_cat_error.jpg" alt="error_img"/>
            </div>
        </div>
    </body>
</html>