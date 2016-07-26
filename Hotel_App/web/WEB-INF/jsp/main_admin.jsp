<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <title>Головна сторінка</title>
    <link href="../../css/admin.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<header>
    <%@include file="admin_header.jsp" %>
</header>
<div id="actions">
    <form action="/apartments">
        <input type="submit" name="delete-insert" value="Номери"/>
    </form>
    <form action="/reservations">
        <input type="submit" name="get-all-reservations" value="Резервації"/>
    </form>
    <form action="/guests">
        <input type="submit" name="get-guests" value="Користувачі"/>
    </form>
</div>
</body>
</html>
