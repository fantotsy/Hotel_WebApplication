<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <title>Admin header</title>
</head>
<body>
<div id="">
    <img src="../../images/admin_icon.png" alt="admin_icon" id="icon"/>
    <h2 id="role">Адміністратор</h2>
</div>
<div id="greeting">
    <h1>Персональна сторінка адміністратора</h1>
</div>
<div>
    <form action="/index" method="post">
        <input type="hidden" name="logout" value="true"/>
        <input type="submit" name="logout" value="Вийти" id="logout"/>
    </form>
</div>
</body>
</html>
