<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <title>Guest extended header</title>
</head>
<body>
<%@include file="guest_header.jsp" %>
<div>
    <form action="/guest">
        <input type="submit" name="submit" value="Повернутися"/>
    </form>
</div>
</body>
</html>
