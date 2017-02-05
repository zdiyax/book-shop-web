<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="temp/header.jsp"/>
<jsp:include page="temp/footer.jsp"/>
<fmt:setBundle basename="lang"/>
<link rel="stylesheet" type="text/css" href="/css/default.css" />
<link rel="stylesheet" type="text/css" href="/css/login-form.css" />
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="login-form">
<form action="${pageContext.request.contextPath}/do/?action=login" method="post">
    <label><b><fmt:message key="index.login"/></b></label><br>
    <p class="placeholder"><fmt:message key="index.login.small"/></p>
    <input type="text" name="login" autofocus> <br><br>
    <label><b><fmt:message key="index.password"/></b></label><br>
    <p class="placeholder"><fmt:message key="index.password.small"/></p>
    <input type="password" name="password"><br><br>
    <button type="submit"><fmt:message key="index.login.button"/></button>
    <p><fmt:message key="login.register.proceedto"/><a href="${pageContext.request.contextPath}/do/?action=show-register-page">here</a></p>
    <br>
</form>
</div>

</body>

</html>
