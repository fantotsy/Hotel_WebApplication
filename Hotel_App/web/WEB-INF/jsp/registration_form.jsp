<%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 17.07.2016
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Реєстраційна форма</title>
</head>
<body>
<div id="form">
    <h1>Реєстраційна форма</h1>
    <form action="/registration" method="post">
        <input type="hidden" name="register" value="pressed"/>
        <div class="row">
            <p>
                <label for="login">Логін</label>
                <c:if test="${error == 'login exists'}">
                    <span id="loginExists">Такий логін вже існує!</span>
                </c:if>
            </p>
            <input type="text" name="login" maxlength="20" value="${guest_data.login}" id="login" required/>
        </div>
        <div class="row">
            <p>
                <label for="password">Пароль</label>
                <c:if test="${error == 'different password and confirmation'}">
                    <span id="differentPasswords">Різні пароль та підтвердження!</span>
                </c:if>
            </p>
            <input type="password" name="password" maxlength="60" id="password" required/>
        </div>
        <div class="row">
            <p>
                <label for="password_confirmation">Підтвердіть пароль</label>
            </p>
            <input type="password" name="password_confirmation" maxlength="60" id="password_confirmation" required/>
        </div>
        <div class="row">
            <p>
                <label for="name">Ім'я</label>
            </p>
            <input type="text" name="name" maxlength="20" value="${guest_data.name}" id="name" required/>
        </div>
        <div class="row">
            <p>
                <label for="surname">Прізвище</label>
            </p>
            <input type="text" name="surname" maxlength="30" value="${guest_data.lastName}" id="surname" required/>
        </div>
        <div class="row">
            <p>
                <label for="pass">Номер паспорта</label>
            </p>
            <input type="text" name="pass" maxlength="20" value="${guest_data.passNumber}" id="pass" required/>
        </div>
        <div class="row">
            <p>
                <label for="email">Електронна пошта</label>
            </p>
            <input type="text" name="email" maxlength="45" value="${guest_data.email}" id="email" required/>
        </div>
        <div class="row">
            <p>
                <label for="phone">Контактний телефон</label>
            </p>
            <input type="text" name="phone" maxlength="20" value="${guest_data.phoneNumber}" id="phone" required/>
        </div>
        <br/>
        <input type="submit" name="submit" value="Зареєструватися"/>
        <input type="reset" name="reset" value="Зкинути"/>
    </form>
</div>
</body>
</html>