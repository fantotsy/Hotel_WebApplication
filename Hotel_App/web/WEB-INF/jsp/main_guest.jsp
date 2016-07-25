<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="err" uri="/WEB-INF/TLDs/errorTag.tld"%>
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
        <%--The next tag prints error message if it is needed--%>
        <err:error errorType="${requestScope.error}" locale="${sessionScope.locale}"/>
        <form action="/booking" method="get">
            <input type="hidden" name="date_chosen" value="true"/>
            <select name="apartment_type[]" multiple id="type_selector">
                <option value="default" disabled selected>Тип номера</option>
                <c:forEach items="${requestScope.listOfTypes}" var="type">
                    <option value="${type}">${type}</option>
                </c:forEach>
            </select>
            <select name="apartment_capacity[]" multiple id="capacity_selector">
                <option value="default" disabled selected>Кількість місць</option>
                <c:forEach items="${requestScope.listOfCapacities}" var="capacity">
                    <option value="${capacity}">${capacity}</option>
                </c:forEach>
            </select>
            <br/>
            <div id="dates">
                <div id="arrival_date">
                    Оберіть дату заселення:
                    <br/>
                    <input type="date" name="check-in_date" max="${requestScope.yearLater}" min="${requestScope.today}"
                           required/>
                </div>
                <div id="departure_date">
                    Оберіть дату виселення:
                    <br/>
                    <input type="date" name="check-out_date" max="${requestScope.yearLater}" min="${requestScope.today}"
                           required/>
                </div>
            </div>
            <br/>
            <input type="submit" name="search" value="Пошук" id="search"/>
        </form>
    </div>
    <div id="reservations_table">
        <h2>Мої замовлення</h2>
        <c:choose>
            <c:when test="${not empty requestScope.listOfReservations}">
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
                    <c:forEach items="${requestScope.listOfReservations}" var="reservation">
                        <tr>
                            <td>${reservation.apartment.apartmentId}</td>
                            <td>${reservation.apartment.category.type}</td>
                            <td>${reservation.apartment.category.numberOfBeds}</td>
                            <td>${reservation.arrival}</td>
                            <td>${reservation.departure}</td>
                            <td>${reservation.totalPrice}</td>
                            <td>
                                <form action="/guest" method="get">
                                    <input type="hidden" name="reservation_id" value="${reservation.reservationId}"/>
                                    <input type="submit" name="cancel_reservation" value="Відмінити"
                                           id="cancel_reservation"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <h2>Список порожній!</h2>
                <h3>Скористайтеся формою зліва для замовлення номерів</h3>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>