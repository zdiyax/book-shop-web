<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="temp/header.jsp"/>
<html>
<head>
    <title>BookGeek - Progressive Book Store!</title>
</head>
<body>
<div align="center">
<form action="${pageContext.request.contextPath}/do/?action=login" method="post">
    <p><fmt:message key="index.login" var="Login please"/></p>
    <label><b><fmt:message key="index.login" var="Login"/></b></label><br>
    <input type="text" name="login"> <br><br>
    <label><b><fmt:message key="index.password" var="Password"/></b></label><br>
    <input type="password" name="password" value="" ><br><br>
    <button type="submit"><fmt:message var="Submit" key="index.login.button"/></button>
    <br>
</form>
</div>
</body>

<jsp:include page="temp/footer.jsp"/>
</html>
