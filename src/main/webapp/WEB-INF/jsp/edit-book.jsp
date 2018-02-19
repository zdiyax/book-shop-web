<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="lang"/>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<fmt:message key="profile.title" var="title"/>
<c:set var="book" value="${book}"/>

<t:snippet title="${title}">
    <jsp:body>
        <div style="margin-top: 60px; margin-left: 60px; width: 60%">
            <form method="post" action="${path}/do/?action=edit-book">
                <h2><fmt:message key="book.details.title.field"/> : </h2>
                <h2><b><input name="title" value="${book.title}" maxlength="32" type="text" autocomplete="off"></b></h2>
                <t:output-errors errors="${titleFormErrors}"/>
                <h4><fmt:message key="book.details.author.field"/> : </h4>
                <h4><b><input name="author" value="${book.author}" maxlength="16" type="text" autocomplete="off"></b></h4>
                <t:output-errors errors="${authorFormErrors}"/>
                <fmt:message key="book.details.price.field"/> :
                <b><input name="price" value="${book.price}" maxlength="5" type="text" autocomplete="off"></b>
                <t:output-errors errors="${priceFormErrors}"/>
                <p><b><fmt:message key="book.details.isbn.field"/> : </b></p>
                <b><input name="isbn" value="${book.isbn}" maxlength="16" type="text" autocomplete="off"></b>
                <t:output-errors errors="${isbnFormErrors}"/>
                <p><b><fmt:message key="book.details.description.field"/> : </b>
                </p><input type="text" name="description" value="${book.description}" style="width: 400px"
                           maxlength="256" autocomplete="off">
                <t:output-errors errors="${descriptionFormErrors}"/>
                <p><b><fmt:message key="book.details.quantity.field"/> : </b></p>
                <b><input name="quantity" value="${book.quantity}" maxlength="4" type="text" autocomplete="off"></b>
                <t:output-errors errors="${quantityFormErrors}"/>
                <br>
                <button type="submit" class="btn btn-outline-primary">
                    <fmt:message key="book.details.button.message"/>
                </button>
            </form>
        </div>
    </jsp:body>
</t:snippet>
