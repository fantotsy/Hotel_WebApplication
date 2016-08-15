<div>
    <img src="../../images/admin_icon.png" alt="admin_icon" id="icon"/>
    <h2 id="role"><fmt:message key="role" bundle="${main_admin}"/></h2>
</div>
<div>
    <form action="/index" method="get">
        <fmt:message var="logout_button_value" key="logout_button" bundle="${main_admin}"/>
        <input type="hidden" name="logout" value="true"/>
        <button type="submit" name="logout" id="logout">${logout_button_value}</button>
    </form>
</div>