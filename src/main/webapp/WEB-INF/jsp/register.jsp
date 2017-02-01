<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="lang"/>
<fmt:message key="register.title" var="title"/>
<fmt:message key="register.login.placeholder" var="loginPlaceholder"/>
<fmt:message key="register.password.placeholder" var="passwordPlaceholder"/>
<fmt:message key="register.password.confirm.placeholder" var="passwordConfirmPlaceholder"/>
<c:set var="registerErrorMessage" value="${registerErrorMessages}"/>
<jsp:include page="temp/header.jsp"/>
<jsp:include page="temp/footer.jsp"/>
<link rel="stylesheet" type="text/css" href="/css/default.css" />
<html>
<head>
    <title>Register</title>
</head>
<body>
<p><fmt:message key="register.hello.message"/></p>

<form action="/do/?action=register" method="post">
    <label><b><fmt:message key="register.login"/></b></label>
    <small><fmt:message key="register.login.range"/></small>
    <br>
    <input type="text" name="login" id="inputLogin" placeholder="${loginPlaceholder}" required
           minlength="4" maxlength="12" value="${sessionScope.login}">
    <br><br>
    <label><b><fmt:message key="register.password"/></b></label>
    <small><fmt:message key="register.password.range"/></small>
    <br>
    <input type="password" name="password" value="" minlength="6" maxlength="12"
           placeholder="${passwordPlaceholder}" required>
    <br>
    <label><b><fmt:message key="register.password.confirm"/></b></label><br>
    <input type="password" name="confirm_password" minlength="6" maxlength="12" value=""
           placeholder="${passwordConfirmPlaceholder}"
           required>

    <br><br>

    <button type="submit"><fmt:message key="register.button.submit"/></button>

</form>
</body>
</html>
