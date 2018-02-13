<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="/WEB-INF/css/jumbotron.css"/>
<link rel="stylesheet" type="text/css" href="/WEB-INF/css/personalinfo.css"/>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
      integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
<fmt:setBundle basename="lang"/>
<c:set var="prefix" value="${pageContext.request.contextPath}"/>
<fmt:message key="profile.title" var="title"/>
<c:set var="role" value="${sessionScope.user.role}"/>
<c:set var="CUSTOMER" value="CUSTOMER"/>
<c:set var="OPERATOR" value="OPERATOR"/>
<c:set var="book" value="${book}"/>

<t:snippet title="${title}">
    <jsp:body>
        <div style="margin-top: 60px; margin-left: 60px; width: 60%">
            <h2><b>${book.title}</b></h2>

            <h4><b>${book.author}</b></h4>
            <h4><b> Price : ${book.price}</b></h4>
            <p> isbn : ${book.isbn}</p>
            <p><b>Description: </b></p>
            <p>${book.description}</p>

            <c:if test="${role == CUSTOMER}">
                <button type="button" class="btn btn-outline-primary">
                    <a style="text-decoration: none" href="/do/?action=add-book-to-cart&id=${book.id}">Add to Cart</a>
                </button>
            </c:if>
            <c:if test="${role == OPERATOR}">
                <button type="button" class="btn btn-outline-primary">
                    <a style="text-decoration: none" href="/do/?action=add-book-to-cart&id=${book.id}">Edit</a>
                </button>
            </c:if>
        </div>
    </jsp:body>
</t:snippet>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
        integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
        crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
        integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
        crossorigin="anonymous"></script>
<script src="../../dist/js/bootstrap.min.js"></script>
