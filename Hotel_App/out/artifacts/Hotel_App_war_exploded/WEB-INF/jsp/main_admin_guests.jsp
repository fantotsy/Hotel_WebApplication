<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <title>Користувачі</title>
    <link href="css/main.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div id="header">
    <div id="">
        <img src="../../images/admin_icon.png" alt="admin_icon" id="icon"/>
        <h2 id="role">Адміністратор</h2>
    </div>
    <div>
        <form action="/index" method="post">
            <input type="submit" name="logout" value="Вийти" id="logout"/>
        </form>
    </div>
</div>
<div id="body">
    <table>
        <tr>
            <th>Ім'я</th>
            <th>Прізвище</th>
            <th>Логін</th>
            <th>Номер телефону</th>
            <th>Електронна пошта</th>
        </tr>
        <c:forEach items="${listOfGuests}" var="guest">
            <tr>
                <td>${guest.name}</td>
                <td>${guest.lastName}</td>
                <td>${guest.login}</td>
                <td>${guest.phoneNumber}</td>
                <td>${guest.email}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>