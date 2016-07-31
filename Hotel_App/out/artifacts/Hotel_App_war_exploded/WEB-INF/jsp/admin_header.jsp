<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
        <%--<title>Admin header</title>--%>
        <link href="../../css/header.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <div>
            <img src="../../images/admin_icon.png" alt="admin_icon" id="icon"/>
            <h2 id="role"><fmt:message key="role" bundle="${main_admin}"/></h2>
        </div>
        <%--<div id="greeting">--%>
            <%--<h1><fmt:message key="greeting" bundle="${main_admin}"/></h1>--%>
        <%--</div>--%>
        <div>
            <form action="/index" method="post">
                <fmt:message var="logout_button_value" key="logout_button" bundle="${main_admin}"/>
                <input type="hidden" name="logout" value="true"/>
                <input type="submit" name="logout" value="${logout_button_value}" id="logout"/>
            </form>
        </div>
    </body>
</html>