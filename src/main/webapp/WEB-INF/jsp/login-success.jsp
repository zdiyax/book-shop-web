<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="temp/header.jsp"/>
<jsp:include page="temp/footer.jsp"/>
<link rel="stylesheet" type="text/css" href="/css/default.css" />
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title>Login Success!</title>
</head>
<body>
<div align="center">
    <h1><b><fmt:message key="login.success.message"/></b></h1>
    <h1> Login Success! Proceed to our store!</h1>
</div>
</body>
</html>
