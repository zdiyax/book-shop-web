<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="lang"/>
<c:set var="prefix" value="${pageContext.request.contextPath}"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/inputform.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/loader.css"/>
<link rel="stylesheet" href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css">

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="refresh" content="2; url=/do/?action=show-my-orders-page">

    <title><fmt:message key="login.success.title"/></title>
</head>

<body class="text-center">
<div class="cssload-container">
    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="order.success.message"/></h1>
    <div class="cssload-speeding-wheel"></div>
</div>
</body>

</html>