<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="lang"/>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:set var="registerErrorMessages" value="${registerFormErrors}"/>
<fmt:message key="register.username.placeholder" var="usernamePlaceholder"/>
<fmt:message key="register.password.placeholder" var="passwordPlaceholder"/>
<fmt:message key="register.confirm_password.placeholder" var="confirm_passwordPlaceholder"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/inputform.css"/>
<link rel="stylesheet" href="https://getbootstrap.com/docs/3.3/dist/css/bootstrap.min.css">


<html>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title><fmt:message key="register.title"/></title>
</head>
<body class="text-center">
<form class="signin" action="/do/?action=register" method="post">
    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="register.hello.message"/></h1>
    <label class="sr-only"></label>
    <input type="text" name="username" class="form-control" placeholder="${usernamePlaceholder}" required autofocus
           minlength="6" maxlength="12">
    <t:output-errors errors="${usernameFormErrors}"/>
    <label class="sr-only"></label>
    <input type="password" name="password" class="form-control" placeholder="${passwordPlaceholder}" required
           minlength="6" maxlength="12">
    <t:output-errors errors="${passwordFormErrors}"/>
    <label class="sr-only"></label>
    <input type="password" name="confirm_password" class="form-control" placeholder="${confirm_passwordPlaceholder}"
           required minlength="6" maxlength="12">
    <t:output-errors errors="${confirm_passwordFormErrors}"/>
    <c:if test="${not empty registerErrorMessages}">
        <div id="errorcolortext"><fmt:message key="${registerFormErrors}"/></div>
    </c:if>
    <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="register.button.message"/></button>
</form>
</body>
</html>