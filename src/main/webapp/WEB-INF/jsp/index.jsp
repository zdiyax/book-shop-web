<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="temp/header.jsp"/>
<html>
<head>
    <title>BookGeek - Progressive Book Store!</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/do/?action=login" method="post">
    <p><fmt:message key="login.message"/></p>
    <label><b><fmt:message key="Lasdasdasd"/></b></label><br>
    <input type="text" name="login"> <br><br>
    <label><b><fmt:message key="Log in"/></b></label><br>
    <input type="password" name="password" value="" ><br><br>
    <button type="submit"><fmt:message key="login.button.submit"/></button>
    <br>
</form>
</body>

<jsp:include page="temp/footer.jsp"/>
</html>
