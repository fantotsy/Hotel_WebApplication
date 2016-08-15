<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="err" uri="/WEB-INF/TLDs/errorTag.tld" %>
<%@ include file="locale.jsp" %>
<fmt:setBundle var="index" basename="ua.fantotsy.properties.i18n.index"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title><fmt:message key="title" bundle="${index}"/></title>
        <link href="../../css/index.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <div id="main_wrapper">
            <div id="language">
                <a href="/index?language=en_US"><img src="../../images/english_language.png" alt="english"
                                                     class="language_image"/></a>
                <a href="/index?language=ua_UA"><img src="../../images/ukrainian_language.png" alt="ukrainian"
                                                     class="language_image"/></a>
            </div>
            <div id="wrapper">
                <header>
                    <h1><fmt:message key="header" bundle="${index}"/></h1>
                    <h2><fmt:message key="greeting" bundle="${index}"/></h2>
                </header>
                <div id="sign_in_wrapper">
                    <h3><fmt:message key="form_header" bundle="${index}"/></h3>
                    <form action="/main" method="post" id="sign_in_form" class="clearfix">
                        <%--The next tag prints error message if it is needed--%>
                        <err:error errorType="${requestScope.error}" locale="${sessionScope.locale}"/>
                        <div>
                            <fmt:message var="login_placeholder" key="login_placeholder" bundle="${index}"/>
                            <input type="text" name="login" placeholder="${login_placeholder}"
                                   value="${sessionScope.login}" class="sign_in_data" required/>
                        </div>
                        <div>
                            <fmt:message var="password_placeholder" key="password_placeholder" bundle="${index}"/>
                            <input type="password" name="password" placeholder="${password_placeholder}"
                                   class="sign_in_data" required/>
                        </div>
                        <div id="adminCheckbox">
                            <input type="checkbox" name="isAdmin" value="true" id="isAdmin">
                            <label for="isAdmin"><fmt:message key="admin_checkBox" bundle="${index}"/></label>
                        </div>
                        <fmt:message var="sign_in_button" key="sign_in_button" bundle="${index}"/>
                        <button type="submit" name="sign_in">${sign_in_button}</button>
                    </form>
                    <form action="/registration" method="get" id="registration">
                        <fmt:message var="registration_button" key="registration_button" bundle="${index}"/>
                        <button type="submit" name="register" value="true">${registration_button}</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>