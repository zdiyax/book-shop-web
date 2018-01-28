<%@tag description="Output errors on jsp" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setBundle basename="lang"/>
<%@attribute name="errors" required="true" type="java.util.List" %>

<c:forEach items="${errors}" var="errorMessage">
    <div id="errorcolortext">
        <fmt:message key="${errorMessage}"/><br/>
        <br/>
    </div>
</c:forEach>