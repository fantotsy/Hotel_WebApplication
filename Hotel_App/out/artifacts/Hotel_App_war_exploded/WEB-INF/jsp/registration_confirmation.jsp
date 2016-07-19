<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Підтвердження реєстрації</title>
        <link href="css/registration_confirmation.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <header id="header">
            <h1>Поздоровляємо, Ви зареєструвалися!</h1>
            <h2>Тепер ви можете резервувати номери готелю</h2>
        </header>
        <div id="wrapper">
            <form action="/main">
                <input type="hidden" name="login" value="${login}"/>
                <input type="hidden" name="password" value="${password}"/>
                <input type="submit" name="next" value="Увійти"/>
            </form>
            <form action="/index">
                <input type="submit" name="next" value="На стартову сторінку"/>
            </form>
        </div>
    </body>
</html>
