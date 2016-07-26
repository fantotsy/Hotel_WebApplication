<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <title>Error</title>
    <link href="../../css/error.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<h1>На жаль, виникла помилка!</h1>
<div id="error_details">
    <h3>Спробуйте виконати одну з наступних дій:</h3>
    <ul>
        <li>Авторизуйтеся;</li>
        <li>Перевірте правильність URL.</li>
    </ul>
    <form action="/index">
        <input type="submit" name="index" value="На стартову сторінку" class="button"/>
    </form>
    <button onclick='history.back()' class="button">На попередню сторінку</button>
</div>
</body>
</html>