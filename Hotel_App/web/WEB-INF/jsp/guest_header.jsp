<div id="role_info">
    <img src="../../images/user_icon.png" alt="user_avatar" id="avatar"/>
    <h2 id="role"><fmt:message key="role" bundle="${main_guest}"/> ${sessionScope.guestInfo.login}</h2>
</div>
<div id="logout_wrapper">
    <form action="/index" method="post">
        <input type="hidden" name="logout" value="true"/>
        <fmt:message var="logout_button" key="logout_button" bundle="${main_guest}"/>
        <button type="submit" name="logout" id="logout">${logout_button}</button>
    </form>
</div>