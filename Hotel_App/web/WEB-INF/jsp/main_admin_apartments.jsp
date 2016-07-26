<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="err" uri="/WEB-INF/TLDs/errorTag.tld" %>
<%@ include file="locale.jsp" %>
<fmt:setBundle var="main_admin" basename="ua.fantotsy.properties.i18n.main_admin"/>
<html>
<head>
    <meta http-equiv="Content-Type" type="text/html; charset=utf-8"/>
    <title>Додати-Видалити</title>
    <link href="../../css/admin.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<header>
    <%@include file="admin_header.jsp" %>
</header>
<div>
    <h1>Таблиця апартаментів</h1>
    <form action="/admin">
        <input type="submit" name="submit" value="Повернутися" id="back"/>
    </form>

    <table id="apartments_table">
        <tr>
            <th>Тип</th>
            <th>Кількість місць</th>
            <th>Ціна (грн/ніч)</th>
            <th>Кількість номерів</th>
            <th>Додати/Видалити</th>
        </tr>
        <c:forEach items="${requestScope.listOfCategories}" var="category">
            <tr>
                <td>${category.type}</td>
                <td>${category.numberOfBeds}</td>
                <td>${category.price}</td>
                <td>${category.apartments}</td>
                <td>
                    <c:if test="${requestScope.error[0] == category.categoryId}">
                        <%--The next tag prints error message if it is needed--%>
                        <err:error errorType="${requestScope.error[1]}" locale="${sessionScope.locale}"/>
                    </c:if>
                    <form action="/apartments" method="get">
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