<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="lang"/>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<fmt:message key="profile.title" var="title"/>
<c:set var="role" value="${sessionScope.user.role}"/>
<c:set var="CUSTOMER" value="CUSTOMER"/>
<c:set var="OPERATOR" value="OPERATOR"/>
<c:set var="book" value="${book}"/>

<t:snippet title="${book.title} ${book.author}">
    <jsp:body>
        <div style="margin-top: 60px; margin-left: 60px; width: 60%">
            <h2><b>${book.title}</b></h2>
            <h4><b>${book.author}</b></h4>
            <h4><b><fmt:message key="book.details.price.field"/> : ${book.price} <fmt:message
                    key="book.details.currency"/></b></h4>
            <p><fmt:message key="book.details.isbn.field"/> : ${book.isbn}</p>
            <p><b><fmt:message key="book.details.description.field"/> :</b></p>
            <p>${book.description}</p>
            <c:if test="${role == CUSTOMER}">
                <button type="button" class="btn btn-outline-primary">
                    <a style="text-decoration: none"
                       href="${path}/do/?action=add-book-to-cart&id=${book.id}"><fmt:message
                            key="book.details.customer.button.message"/></a>
                </button>
            </c:if>
            <c:if test="${role == OPERATOR}">
                <button type="button" class="btn btn-outline-primary">
                    <a style="text-decoration: none" href="${path}/do/?action=show-edit-book-page"><fmt:message
                            key="book.details.operator.button.message"/></a>
                </button>
            </c:if>
        </div>
    </jsp:body>
</t:snippet>
