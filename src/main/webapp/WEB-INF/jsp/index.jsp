<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="temp/header.jsp"/>
<link rel="stylesheet" type="text/css" href="/css/default.css" />
<fmt:setBundle basename="lang"/>
<c:set var="prefix" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>BookGeek - Progressive Book Store!</title>
</head>
<body>
<div class="welcome">
    <h1>Welcome to BookGeek - Progressive Book Store!</h1>
    <p>
        We enjoy bringing the best service to our customers!
        Please, proceed to Catalog page to browse our goods.
    </p>

    <p>
        If you want to proceed in our store, please login <a href="${prefix}/do/?action=show-login-page">here</a>.
    </p>
</div>

</body>

<jsp:include page="temp/footer.jsp"/>
</html>
