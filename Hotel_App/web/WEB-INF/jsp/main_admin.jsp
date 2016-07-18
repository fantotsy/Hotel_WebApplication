<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <title>Головна сторінка</title>
    <link href="css/main.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<%@include file="admin_header.jsp" %>
<form action="/main_admin_apartments">
    <input type="submit" name="delete-insert" value="Номери"/>
</form>
<form action="/main_admin_reservations">
    <input type="submit" name="get-all-reservations" value="Резервації"/>
</form>
<form action="/main_admin_guests">
    <input type="submit" name="get-guests" value="Користувачі"/>
</form>
</body>
</html>
