<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="err" uri="/WEB-INF/TLDs/errorTag.tld" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    </head>
    <body>
        <div id="form">
            <h1 id="form_title"><fmt:message key="form_title" bundle="${registration}"/></h1>
            <form action="/index" method="post" id="back_button">
                <fmt:message var="back_button" key="back_button" bundle="${registration}"/>
                <input type="submit" name="to_start_page" value="${back_button}"/>
            </form>
            <form action="/registration" method="post">
                <input type="hidden" name="register" value="pressed"/>
                <div class="row">
                    <p>
                        <label for="login"><fmt:message key="login_label" bundle="${registration}"/></label>
                        <%--The next tag prints error message if it is needed--%>
                        <err:error errorType="${requestScope.error_login}" locale="${sessionScope.locale}"/>
                    </p>
                    <input type="text" name="login" maxlength="20" value="${requestScope.guest_data.login}" id="login"
                           required/>
                </div>
                <div class="row">
                    <p>
                        <label for="password"><fmt:message key="password_label" bundle="${registration}"/></label>
                        <%--The next tag prints error message if it is needed--%>
                        <err:error errorType="${requestScope.error_password}" locale="${sessionScope.locale}"/>
                    </p>
                    <input type="password" name="password" maxlength="60" id="password" required/>
                </div>
                <div class="row">
                    <p>
                        <label for="password_confirmation"><fmt:message key="password_confirmation_label"
                                                                        bundle="${registration}"/></label>
                    </p>
                    <input type="password" name="password_confirmation" maxlength="60" id="password_confirmation" required/>
                </div>
                <div class="row">
                    <p>
                        <label for="name"><fmt:message key="name_label" bundle="${registration}"/></label>
                    </p>
                    <input type="text" pattern="[A-Za-zА-Яa-яЄєІіЇїЙйЁё]+" name="name" maxlength="20"
                           value="${requestScope.guest_data.name}"
                           id="name" required/>
                </div>
                <div class="row">
                    <p>
                        <label for="surname"><fmt:message key="surname_label" bundle="${registration}"/></label>
                    </p>
                    <input type="text" pattern="[A-Za-zА-Яa-яЄєІіЇїЙйЁё]+" name="surname" maxlength="30"
                           value="${requestScope.guest_data.lastName}" id="surname" required/>
                </div>
                <div class="row">
                    <p>
                        <label for="email"><fmt:message key="email_label" bundle="${registration}"/></label>
                    </p>
                    <input type="text" pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$"
                           name="email" maxlength="45" value="${requestScope.guest_data.email}" id="email" required/>
                </div>
                <div class="row">
                    <p>
                        <label for="phone"><fmt:message key="phone_number_label" bundle="${registration}"/></label>
                    </p>
                    +380<input type="text" pattern="[0-9]{9}" name="phone" maxlength="20"
                               value="${requestScope.guest_data.phoneNumber}"
                               id="phone" required/>
                </div>
                <br/>
                <fmt:message var="register_button" key="register_button" bundle="${registration}"/>
                <input type="submit" name="submit" value="${register_button}"/>
                <fmt:message var="reset_button" key="reset_button" bundle="${registration}"/>
                <input type="reset" name="reset" value="${reset_button}"/>
            </form>
        </div>
    </body>
</html>