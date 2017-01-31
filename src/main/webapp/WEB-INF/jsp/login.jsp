<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="temp/header.jsp"/>
<jsp:include page="temp/footer.jsp"/>
<fmt:setBundle basename="lang"/>
<link rel="stylesheet" type="text/css" href="/css/default.css" />
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/do/?action=login" method="post">
    <label><b><fmt:message key="index.login"/></b></label><br>
    <input type="text" name="login"> <br><br>
    <label><b><fmt:message key="index.password"/></b></label><br>
    <input type="password" name="password" value="" ><br><br>
    <button type="submit"><fmt:message key="index.login.button"/></button>
    <p><fmt:message key="login.register.proceedto"/></p>
    <br>
</form>
</body>
</html>
