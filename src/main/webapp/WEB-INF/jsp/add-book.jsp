<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="lang"/>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<fmt:message key="operator.add.book.title" var="title"/>

<t:snippet title="${title}">
    <jsp:body>
        <div style="margin-top: 60px; margin-left: 60px; width: 60%">
            <form method="post" action="/do/?action=add-book">
                <h2><fmt:message key="book.details.title.field"/> : </h2>
                <h2><b><input name="title" required></b></h2>
                <h4><fmt:message key="book.details.author.field"/> : </h4>
                <h4><b><input name="author" required></b></h4>
                <fmt:message key="book.details.price.field"/> :
                <b><input name="price" required></b>
                <p><fmt:message key="book.details.isbn.field"/> :
                    <b><input name="isbn" required></b></p>
                <p><b><fmt:message key="book.details.description.field"/> : </b>
                </p><input type="text" name="description" style="width: 400px" required>
                <p><b><fmt:message key="book.details.quantity.field"/> : </b></p>
                <b><input name="quantity" required></b>
                <br>
                <button type="submit" class="btn btn-outline-primary">
                    <fmt:message key="book.details.button.message"/>
                </button>
            </form>
        </div>
    </jsp:body>
</t:snippet>
