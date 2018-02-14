<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/WEB-INF/css/jumbotron.css"/>
<link rel="stylesheet" href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css">

<fmt:setBundle basename="lang"/>
<c:set var="prefix" value="${pageContext.request.contextPath}"/>
<fmt:message key="catalog.title" var="title"/>
<c:set var="books" value="${books}"/>
<c:set var="totalCost" value="${totalCost}"/>
<c:set var="cartFormErrors" value="${cartFormErrors}"/>

<t:snippet title="${title}">
    <jsp:body>
        <div style="width: 20%; height: 80%; float:left; margin-top: 100px;">
        </div>
        <div style="width: 80%; height: 80%; float:right; margin-top: 100px;">
            <div class="container">
                <c:if test="${not empty cartFormErrors}">
                    <p><fmt:message key="${cartFormErrors}"/></p>
                </c:if>
                <form name="Form1" method="post" action="/do/?action=order-books">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col"><fmt:message key="catalog.authorField"/></th>
                            <th scope="col"><fmt:message key="catalog.titleField"/></th>
                            <th scope="col" style="width: 5%"><fmt:message key="catalog.priceField"/></th>
                            <th scope="col" style="width: 5%">Quantity</th>
                            <th style="width: 2%"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="cart" items="${cart}" varStatus="i">
                            <tr>
                                <td>${cart.key.author}</td>
                                <td>${cart.key.title}</td>
                                <td>${cart.key.price}</td>
                                <td><input class="form-control" value="${cart.value}"
                                           name="quantity${i.index}"
                                           autocomplete="off" pattern="\d*[1-9]\d* " maxlength="2"/></td>
                                <td><a href="/do/?action=delete-book-from-cart&book=${cart.key.id}">x</a></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td style="visibility: collapse"></td>
                            <td><b>Total cost :</b></td>
                            <td>${totalCost}</td>
                        </tr>
                        <tr>
                            <td style="visibility: collapse"></td>
                            <td style="visibility: collapse"></td>
                            <td><button class="btn btn-outline-primary" type="submit" name="order"
                                        onclick="return onOrder();">Order</button></td>
                        </tr>
                        </tbody>
                    </table>
                    <p>
                        <button class="btn btn-outline-primary" name="refresh" onclick="return onRefresh();">
                            &#8635;
                        </button>
                    </p>
                </form>

            </div>
        </div>
    </jsp:body>
</t:snippet>


<%--JavaScript to upload info from the same form by two actions--%>
<script language="Javascript">
    function onOrder() {
        document.Form1.action = "/do/?action=order-books";
        document.Form1.submit();             // Submit the page
        return true;
    }

    function onRefresh() {
        document.Form1.action = "/do/?action=refresh-cart";
        document.Form1.submit();             // Submit the page
        return true;
    }
</script>

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