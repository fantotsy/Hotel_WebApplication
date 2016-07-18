<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
        <title>Замовлення</title>
        <link href="css/main_guest.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <header>
            <%@include file="guest_header.jsp" %>
        </header>
        <div id="wrapper">
            <div id="order_form">
                <h2>Оберіть саме ті номери, які вас цікавлять</h2>
                <c:if test="${error == 'arrival is later than departure'}">
                    <span id="wrong_date">Помилкові дані: дата заселення має бути раніше дати виселення!</span>
                </c:if>
                <c:if test="${error == 'empty types or capacities'}">
                    <span id="wrong_apartment_data">Помилкові дані: тип та кількість місць мають бути заповненими!</span>
                </c:if>
                <form action="/booking" method="get">
                    <input type="hidden" name="date_chosen" value="true"/>
                    <select name="apartment_type[]" multiple id="type_selector">
                        <option value="default" disabled selected>Тип номера</option>
                        <c:forEach items="${listOfTypes}" var="type">
                            <option value="${type}">${type}</option>
                        </c:forEach>
                    </select>
                    <select name="apartment_capacity[]" multiple id="capacity_selector">
                        <option value="default" disabled selected>Кількість місць</option>
                        <c:forEach items="${listOfCapacities}" var="capacity">
                            <option value="${capacity}">${capacity}</option>
                        </c:forEach>
                    </select>
                    <br/>
                    <div id="dates">
                        <div id="arrival_date">
                            Оберіть дату заселення:
                            <br/>
                            <input type="date" name="check-in_date" max="${year_later}" min="${today}" required/>
                        </div>
                        <div id="departure_date">
                            Оберіть дату виселення:
                            <br/>
                            <input type="date" name="check-out_date" max="${year_later}" min="${today}" required/>
                        </div>
                    </div>
                    <br/>
                    <input type="submit" name="search" value="Пошук"/>
                </form>
            </div>
            <div id="reservations_table">
                <c:choose>
                    <c:when test="${not empty listOfReservations}">
                        <h2>Мої замовлення</h2>
                        <table>
                            <tr>
                                <th>Номер</th>
                                <th>Тип</th>
                                <th>Кількість місць</th>
                                <th>Заселення</th>
                                <th>Виселення</th>
                                <th>Вартість</th>
                                <th></th>
                            </tr>
                            <c:forEach items="${listOfReservations}" var="reservation">
                                <tr>
                                    <td>${reservation.apartment.apartmentId}</td>
                                    <td>${reservation.apartment.category.type}</td>
                                    <td>${reservation.apartment.category.numberOfBeds}</td>
                                    <td>${reservation.arrival}</td>
                                    <td>${reservation.departure}</td>
                                    <td>${reservation.totalPrice}</td>
                                    <td>
                                        <form action="/main_guest" method="get">
                                            <input type="hidden" name="reservation_id" value="${reservation.reservationId}"/>
                                            <input type="submit" name="cancel_reservation" value="Відмінити" id="book_room"/>
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
            </div>
        </div>
    </body>
</html>