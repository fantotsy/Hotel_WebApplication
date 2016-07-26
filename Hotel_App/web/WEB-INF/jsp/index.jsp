<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="err" uri="/WEB-INF/TLDs/errorTag.tld" %>
<%@ include file="locale.jsp" %>
<fmt:setBundle var="index" basename="ua.fantotsy.properties.i18n.index"/>
<html>
    <head>
        <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
        <title><fmt:message key="title" bundle="${index}"/></title>
        <link href="../../css/index.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <a href="/index?language=en_US"><img src="../../images/english_language.jpg" alt="english" class="language_image"/></a>
        <a href="/index?language=ua_UA"><img src="../../images/ukrainian_language.jpg" alt="ukrainian" class="language_image"/></a>
        <div id="wrapper">
            <header id="header">
                <h1><fmt:message key="header" bundle="${index}"/></h1>
                <h2><fmt:message key="greeting" bundle="${index}"/></h2>
            </header>
            <div id="signin_wrapper">
                <h3><fmt:message key="form_header" bundle="${index}"/></h3>
                <form action="/main" method="post" id="signin_form">
                    <%--The next tag prints error message if it is needed--%>
                    <err:error errorType="${requestScope.error}" locale="${sessionScope.locale}"/>
                    <div>
                        <fmt:message var="login_placeholder" key="login_placeholder" bundle="${index}"/>
                        <input type="text" name="login" placeholder="${login_placeholder}" value="${sessionScope.login}"
                               class="signin_data" required/>
                    </div>
                    <div>
                        <fmt:message var="password_placeholder" key="password_placeholder" bundle="${index}"/>
                        <input type="password" name="password" placeholder="${password_placeholder}" class="signin_data"
                               required/>
                    </div>
                    <label id="adminCheckbox">
                        <input type="checkbox" name="isAdmin" value="true">
                        <fmt:message key="admin_checkBox" bundle="${index}"/>
                    </label>
                    <fmt:message var="signIn_button" key="signIn_button" bundle="${index}"/>
                    <input type="submit" name="signin" value="${signIn_button}" class="submit"/>
                </form>
                <form action="/registration" method="post" id="registration">
                    <fmt:message var="registration_button" key="registration_button" bundle="${index}"/>
                    <input type="submit" name="register" value="${registration_button}" class="submit"/>
                </form>
            </div>
        </div>
    </body>
</html>