<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="lang"/>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<fmt:message key="login.username.placeholder" var="usernamePlaceholder"/>
<fmt:message key="login.password.placeholder" var="passwordPlaceholder"/>
<c:set var="loginErrorMessages" value="${loginFormErrors}"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/inputform.css"/>
<link rel="stylesheet" href="https://getbootstrap.com/docs/3.3/dist/css/bootstrap.min.css">

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><fmt:message key="login.title"/></title>
</head>

<body class="text-center">
<form class="signin" action="${pageContext.request.contextPath}/do/?action=login" method="post">
    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="login.title"/></h1>
    <label class="sr-only"></label>
    <input type="text" name="username" class="form-control" placeholder="${usernamePlaceholder}" required autofocus
           minlength="6" maxlength="12">
    <label class="sr-only"></label>
    <input type="password" name="password" class="form-control" placeholder="${passwordPlaceholder}" required
           minlength="6" maxlength="12">
    <c:if test="${not empty loginErrorMessages}">
        <div><fmt:message key="${loginFormErrors}"/></div>
    </c:if>
    <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="login.button.message"/></button>
    <p></p>
    <p class="mt-5 mb-3 text-muted"><fmt:message key="login.register.proceed.to"/><a
            href="${path}/do/?action=show-register-page"><fmt:message key="login.register.proceed.to.here"/></a></p>
</form>
</body>
</html>
