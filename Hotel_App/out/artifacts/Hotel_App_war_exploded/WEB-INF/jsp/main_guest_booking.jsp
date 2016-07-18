<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <title>Бронювання</title>
    <link href="css/main.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div id="header">
    <div id="">
        <img src="../../images/user_icon.png" alt="user_icon" id="icon"/>
        <h2 id="role">Користувач</h2>
    </div>
    <div>
        <form action="/index" method="post">
            <input type="submit" name="logout" value="Вийти" id="logout"/>
        </form>
    </div>
</div>
<form action="/main_guest">
    <input type="submit" name="submit" value="Повернутися"/>
</form>
<c:choose>
    <c:when test="${not empty listOfCategories}">
        <table>
            <tr>
                <th>Тип</th>
                <th>Кількість місць</th>
                <th>Ціна (грн/ніч)</th>
                <th>Кількість номерів</th>
                <th></th>
            </tr>
            <c:forEach items="${listOfCategories}" var="category">
                <tr>
                    <td>${category.type}</td>
                    <td>${category.numberOfBeds}</td>
                    <td>${category.price}</td>
                    <td>${category.apartments}</td>
                    <td>
                        <c:if test="${category_id == category.categoryId and error == 'apartments array is empty'}">
                            Порожнє замовлення!
                        </c:if>
                        <form action="/order_valid" method="get">
                            <input type="hidden" name="category_id" value="${category.categoryId}"/>
                            <select name="booked_apartments[]" multiple>
                                <option value="default" selected disabled>Оберіть номери</option>
                                <c:forEach items="${listOfApartments}" var="apartment">
                                    <c:if test="${apartment.value == category.categoryId}">
                                        <option value="${apartment.key}">${apartment.key}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            <input type="submit" name="book_room" value="Забронювати" id="book_room"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        Список порожній!
    </c:otherwise>
</c:choose>
</body>
</html>
