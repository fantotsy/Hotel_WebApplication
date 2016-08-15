<header id="header">
    <h1><fmt:message key="greeting" bundle="${registration}"/></h1>
    <h2><fmt:message key="info" bundle="${registration}"/></h2>
</header>
<div id="wrapper">
    <form action="/main" method="post">
        <input type="hidden" name="login" value="${login}"/>
        <input type="hidden" name="just_registered" value="true"/>
        <fmt:message var="signIn_button" key="signIn_button" bundle="${registration}"/>
        <button type="submit" name="sign_in" id="sign_in_button">${signIn_button}</button>
    </form>
    <form action="/index" method="get">
        <fmt:message var="startPage_button" key="startPage_button" bundle="${registration}"/>
        <button type="submit" name="to_start_page" id="to_start_page_button">${startPage_button}</button>
    </form>
</div>