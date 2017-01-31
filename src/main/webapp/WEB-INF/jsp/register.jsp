<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<jsp:body>
    <p><fmt:message key="register.hello.message"/></p>
    <form name="registerForm" action="/do/?action=register" method="post">
        <label><b><fmt:message key="register.login"/></b></label>
        <small><fmt:message key="register.login.range"/></small>
        <br>
        <input type="text" name="login" id="inputLogin" placeholder="${loginPlaceholder}" required autofocus
               minlength="3" maxlength="12" value="${sessionScope.login}">
        <t:output-errors errors="${loginErrorMessages}"/>
        <br><br>
        <label><b><fmt:message key="register.password"/></b></label>
        <small><fmt:message key="register.password.range"/></small>
        <br>
        <input type="password" name="password" value="" minlength="6" maxlength="16"
               placeholder="${passwordPlaceholder}" required
               onchange="form.confirm_password.pattern = this.value;">
        <t:output-errors errors="${passwordErrorMessages}"/>
        <br>
        <label><b><fmt:message key="register.password.confirm"/></b></label><br>
        <input type="password" name="confirm_password" minlength="6" maxlength="16" value=""
               placeholder="${passwordConfirmPlaceholder}"
               required>

        <t:output-errors errors="${confirm_passwordErrorMessages}"/>
        <br><br>

        <button type="submit"><fmt:message key="register.button.submit"/></button>
        <c:if test="${not empty registerErrorMessage}">
            <div id="errorcolortext"><fmt:message key="${registerErrorMessages}"/></div>
        </c:if>

    </form>
</jsp:body>
</body>
</html>
