<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="lang"/>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<fmt:message key="500.title" var="title"/>

<t:snippet title="${title}">
    <jsp:body>
        <div class="jumbotron">
            <div class="container">
                <h1 class="display-3">500</h1>
                <p><fmt:message key="500.message"/></p>
            </div>
        </div>
    </jsp:body>
</t:snippet>
