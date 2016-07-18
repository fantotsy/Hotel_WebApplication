<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <title>Guest header</title>
</head>
<body>
<div id="">
    <img src="../../images/user_icon.png" alt="user_icon" id="icon"/>
    <h2 id="role">Користувач</h2>
</div>
<div>
    <form action="/index" method="post">
        <input type="submit" name="logout" value="Вийти" id="logout"/>
    </form>
</div>
</body>
</html>
