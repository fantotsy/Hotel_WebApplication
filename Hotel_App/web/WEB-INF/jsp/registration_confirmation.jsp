<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    </head>
    <body>
        <header id="header">
            <h1><fmt:message key="greeting" bundle="${registration}"/></h1>
            <h2><fmt:message key="info" bundle="${registration}"/></h2>
        </header>
        <div id="wrapper">
            <form action="/main">
                <input type="hidden" name="login" value="${login}"/>
                <input type="hidden" name="just_registered" value="true"/>
                <fmt:message var="signIn_button" key="signIn_button" bundle="${registration}"/>
                <input type="submit" name="sign_in" value="${signIn_button}" id="sign_in_button"/>
            </form>
            <form action="/index">
                <fmt:message var="startPage_button" key="startPage_button" bundle="${registration}"/>
                <input type="submit" name="to_start_page" value="${startPage_button}" id="to_start_page_button"/>
            </form>
        </div>
    </body>
</html>