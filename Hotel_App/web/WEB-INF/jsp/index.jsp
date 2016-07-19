<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
        <title>Стартова сторінка</title>
        <link href="../../css/index.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
    <div id="wrapper">
        <header id="header">
            <h1>Резерв готельних номерів</h1>
            <h2>Ласкаво просимо!</h2>
        </header>
        <div id="signin_wrapper">
            <h3>Вхід в особистий кабінет</h3>
            <form action="/main" method="post" id="signin_form">
                <c:if test="${error == 'wrong entrance data'}">
                    <span>Помилковий логін або пароль!</span>
                </c:if>
                <div>
                    <input type="text" name="login" placeholder="Логін" value="${sessionScope.login}" class="signin_data"
                           required/>
                </div>
                <div>
                    <input type="password" name="password" placeholder="Пароль" class="signin_data" required/>
                </div>
                <label id="adminCheckbox">
                    <c:if test="${sessionScope.role != 'admin'}">
                        <input type="checkbox" name="isAdmin" value="true">
                    </c:if>
                    <c:if test="${sessionScope.role == 'admin'}">
                        <input type="checkbox" name="isAdmin" value="true" checked>
                    </c:if>
                    Адміністратор
                </label>
                <input type="submit" name="signin" value="Увійти" class="submit"/>
            </form>
            <form action="/registration" method="post" id="registration">
                <input type="submit" name="register" value="Зареєструватися" class="submit"/>
            </form>
        </div>
    </div>
    </body>
</html>