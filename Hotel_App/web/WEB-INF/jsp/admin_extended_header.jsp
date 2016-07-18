<%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 18.07.2016
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin extended header</title>
</head>
<body>
    <%@include file="admin_header.jsp"%>
    <div>
        <form action="/main_admin">
            <input type="submit" name="submit" value="Повернутися"/>
        </form>
    </div>
</body>
</html>
