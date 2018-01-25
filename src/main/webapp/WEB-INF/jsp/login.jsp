<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="lang"/>
<c:set var="prefix" value="${pageContext.request.contextPath}"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/inputform.css"/>
<link rel="stylesheet" href="https://getbootstrap.com/docs/3.3/dist/css/bootstrap.min.css">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">>
    <link rel="icon" href="../../../../favicon.ico">
    <title>Login here</title>
</head>

<body class="text-center">
<form class="signin" action="${pageContext.request.contextPath}/do/?action=login" method="post">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <label class="sr-only">Username</label>
    <input type="text" name="username" class="form-control" placeholder="username" required autofocus>
    <label class="sr-only">Password</label>
    <input type="password" name="inputPassword" class="form-control" placeholder="Password" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <p></p>
    <p class="mt-5 mb-3 text-muted">Not registered yet?
        Click <a href="${prefix}/do/?action=show-register-page">here</a></p>
</form>
</body

</html>
