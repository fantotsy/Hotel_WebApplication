<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <link href="../../css/header.css" type="text/css" rel="stylesheet"/>
</head>
<body>

<div>
    <img src="../../images/user_icon.png" alt="user_icon" id="icon"/>
    <h2 id="role">Користувач</h2>
</div>
<div id="greeting">
    <h1>Персональна сторінка користувача ${sessionScope.guestInfo.login}</h1>
</div>
<div>
    <form action="/index" method="post">
        <input type="hidden" name="logout" value="true"/>
        <input type="submit" name="logout" value="Вийти" id="logout"/>
    </form>
</div>
</body>
</html>
