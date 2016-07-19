<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
    <head>
        <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
        <title>Error</title>
        <link href="../../css/error.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <h1>На жаль, виникла помилка!</h1>
        <h2>Спробуйте виконати одну з наступних дій:</h2>
        <ul >
            <li>Авторизуйтеся;</li>
            <li>Перевірте правильність URL.</li>
        </ul>
        <jsp:expression>exception.toString()</jsp:expression>
    </body>
</html>