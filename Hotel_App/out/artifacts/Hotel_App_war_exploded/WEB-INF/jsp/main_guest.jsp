<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <title>Замовлення</title>
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
<div id="body">
    <c:choose>
        <c:when test="${sessionScope.date_chosen eq 'true'}">
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
                            ${listOfCategories}
                        <c:forEach items="${listOfCategories}" var="category">
                            <tr>
                                <td>${category.type}</td>
                                <td>${category.numberOfBeds}</td>
                                <td>${category.price}</td>
                                <td>
                                        ${category.apartments}
                                </td>
                                <td>
                                    <c:if test="${emptyOrder == 'true'}">
                                        Порожнє замовлення!
                                    </c:if>
                                    <form action="/main-user-booked" method="get">
                                        <input type="hidden" name="category_id" value="${category.categoryId}"/>
                                        <select name="booked_numbers[]" multiple>
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
        </c:when>
        <c:otherwise>
            <div id="order_form">
                <c:if test="${wrongDates eq 'true'}">
                    <span id="wrong_date">Помилкові дані: дата заселення має бути раніше дати виселення!</span>
                </c:if>
                <c:if test="${wrongApartmentData eq 'true'}">
                    <span id="wrong_apartment_data">Помилкові дані: тип та кількість місць мають бути заповненими!</span>
                </c:if>
                <form action="/date-chosen" method="get">
                    <input type="hidden" name="date_chosen" value="true"/>
                    <select name="apartment_type[]" multiple>
                        <option value="default" disabled selected>Тип номера</option>
                        <c:forEach items="${listOfTypes}" var="type">
                            <option value="${type}">${type}</option>
                        </c:forEach>
                    </select>
                    <select name="apartment_capacity[]" multiple>
                        <option value="default" disabled selected>Кількість місць</option>
                        <c:forEach items="${listOfCapacities}" var="capacity">
                            <option value="${capacity}">${capacity}</option>
                        </c:forEach>
                    </select>
                    Оберіть дату заселення:
                    <input type="date" name="check-in_date" max="${year_later}" min="${today}" required>
                    Оберіть дату виселення:
                    <input type="date" name="check-out_date" max="${year_later}" min="${today}" required>
                    <input type="submit" name="search" value="Пошук">
                </form>
            </div>
        </c:otherwise>
    </c:choose>

</div>
</body>
</html>