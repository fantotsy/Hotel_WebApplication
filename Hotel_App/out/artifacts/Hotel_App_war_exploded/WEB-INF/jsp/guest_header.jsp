<%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 18.07.2016
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
