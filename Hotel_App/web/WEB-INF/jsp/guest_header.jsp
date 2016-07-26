<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
        <link href="../../css/header.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <div>
            <img src="../../images/user_icon.png" alt="user_icon" id="icon"/>
            <h2 id="role"><fmt:message key="role" bundle="${main_guest}"/></h2>
        </div>
        <div id="greeting">
            <h1><fmt:message key="greeting" bundle="${main_guest}"/>. Login: ${sessionScope.guestInfo.login}</h1>
        </div>
        <div>
            <form action="/index" method="post">
                <input type="hidden" name="logout" value="true"/>
                <fmt:message var="logout_button" key="logout_button" bundle="${main_guest}"/>
                <input type="submit" name="logout" value="${logout_button}" id="logout"/>
            </form>
        </div>
    </body>
</html>