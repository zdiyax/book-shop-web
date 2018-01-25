<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="lang"/>
<c:set var="prefix" value="${pageContext.request.contextPath}"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/inputform.css"/>
<link rel="stylesheet" href="https://getbootstrap.com/docs/3.3/dist/css/bootstrap.min.css">


<html>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Register</title>
</head>
    <body class="text-center">
    <form class="signin" action="/do/?action=register" method="post">
        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
        <label class="sr-only">Username</label>
        <input type="text" name="username" class="form-control" placeholder="username" required autofocus>
        <label class="sr-only">Password</label>
        <input type="password" name="password" class="form-control" placeholder="Password" required>
        <label class="sr-only">Password</label>
        <input type="password" name="confirm_password" class="form-control" placeholder="ConfirmPassword" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
    </form>
    </body>
</html>