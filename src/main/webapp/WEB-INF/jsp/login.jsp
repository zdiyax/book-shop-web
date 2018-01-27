<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="lang"/>
<c:set var="prefix" value="${pageContext.request.contextPath}"/>
<fmt:message key="login.username.placeholder" var="usernamePlaceholder"/>
<fmt:message key="login.password.placeholder" var="passwordPlaceholder"/>
<c:set var="loginErrorMessage" value="${loginFormMessages}"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/inputform.css"/>
<link rel="stylesheet" href="https://getbootstrap.com/docs/3.3/dist/css/bootstrap.min.css">

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="../../../../favicon.ico">
    <title><fmt:message key="signin.message"/></title>
</head>

<body class="text-center">
<form class="signin" action="${pageContext.request.contextPath}/do/?action=login" method="post">
    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="signin.message"/></h1>
    <label class="sr-only"><fmt:message key="login.username"/></label>
    <input type="text" name="username" class="form-control" placeholder="${usernamePlaceholder}" required autofocus
           minlength="6" maxlength="12">
    <label class="sr-only"><fmt:message key="login.password"/></label>
    <input type="password" name="password" class="form-control" placeholder="${passwordPlaceholder}" required
           minlength="6" maxlength="12">
    <c:if test="${not empty loginErrorMessage}">
        <div id="errorcolortext"><fmt:message key="${loginFormMessages}"/></div>
    </c:if>
    <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="signin.message"/></button>
    <p></p>
    <p class="mt-5 mb-3 text-muted"><fmt:message key="login.register.proceedto"/><a
            href="${prefix}/do/?action=show-register-page"><fmt:message key="login.register.proceedto.here"/></a></p>
</form>
</body>
</html>
