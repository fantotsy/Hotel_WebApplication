<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <title>Додати-Видалити</title>
    <link href="css/main.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<%@include file="admin_extended_header.jsp" %>
<div id="body">
    <table>
        <tr>
            <th>Тип</th>
            <th>Кількість місць</th>
            <th>Ціна (грн/ніч)</th>
            <th>Кількість номерів</th>
            <th>Додати/Видалити</th>
        </tr>
        <c:forEach items="${listOfCategories}" var="category">
            <tr>
                <td>${category.type}</td>
                <td>${category.numberOfBeds}</td>
                <td>${category.price}</td>
                <td>${category.apartments}</td>
                <td>
                    <c:if test="${error[0] == category.categoryId}">
                        <c:if test="${error[1] == 'current apartment exists'}">
                            Такий номер вже існує!
                        </c:if>
                        <c:if test="${error[1] == 'current apartment does not exist'}">
                            Такого номера не існує в даній категорії!
                        </c:if>
                    </c:if>
                    <form action="/main_admin_apartments" method="get">
                        <input type="hidden" name="category_id" value="${category.categoryId}"/>
                        <input type="text" pattern="[0-9]{3}" name="apartment_number" placeholder="номер" required/>
                        <input type="submit" name="add_apartment" value="Додати" id="add_room"/>
                        <input type="submit" name="remove_apartment" value="Видалити" id="remove_room"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>