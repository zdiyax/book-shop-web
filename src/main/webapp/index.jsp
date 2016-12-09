<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 12/1/2016
  Time: 8:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/temp/header.jsp" />
<html>
<head>
    <title>Book Shop</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/do/?action=login" method="post">
    <p><fmt:message key="login.message"/></p>
    <label><b><fmt:message key="register.login"/></b></label><br>
    <input type="text" name="login"> <br><br>
    <label><b><fmt:message key="register.password"/></b></label><br>
    <input type="password" name="password" value="" ><br><br>
    <button type="submit"><fmt:message key="login.button.submit"/></button>
    <br>
</form>
</body>

<jsp:include page="/WEB-INF/temp/footer.jsp"/>
</html>
