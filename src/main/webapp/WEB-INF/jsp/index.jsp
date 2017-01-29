<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="temp/header.jsp"/>

<fmt:setBundle basename="lang/"/>

<html>
<head>
    <title>BookGeek - Progressive Book Store!</title>
</head>
<body>
<div align="center">
<form action="${pageContext.request.contextPath}/do/?action=login" method="post">
    <label><b><fmt:message key="index.login"/></b></label><br>
    <input type="text" name="login"> <br><br>
    <label><b><fmt:message key="index.password"/></b></label><br>
    <input type="password" name="password"><br><br>
    <button type="submit"><fmt:message key="index.login.button"/></button>
    <br>
</form>
</div>
</body>

<jsp:include page="temp/footer.jsp"/>
</html>
