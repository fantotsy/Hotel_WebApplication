<%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 18.07.2016
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin header</title>
</head>
<body>
<div id="">
    <img src="../../images/admin_icon.png" alt="admin_icon" id="icon"/>
    <h2 id="role">Адміністратор</h2>
</div>
<div>
    <form action="/index" method="post">
        <input type="submit" name="logout" value="Вийти" id="logout"/>
    </form>
</div>
</body>
</html>
