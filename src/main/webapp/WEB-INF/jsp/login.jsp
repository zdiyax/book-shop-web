<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/do/?action=login" method="post">
    <p><fmt:message key="login "/></p>
    <label><b><fmt:message key="register.login"/></b></label><br>
    <input type="text" name="login"> <br><br>
    <label><b><fmt:message key="register.password"/></b></label><br>
    <input type="password" name="password" value="" ><br><br>
    <button type="submit"><fmt:message key="login.button.submit"/></button>
    <br>
</form>
</body>
</html>
