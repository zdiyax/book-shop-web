<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="lang"/>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<fmt:message key="cart.title" var="title"/>
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
                <form name="cartForm" method="post" action="${path}/do/?action=order-books">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col"><fmt:message key="cart.author.field"/></th>
                            <th scope="col"><fmt:message key="cart.title.field"/></th>
                            <th scope="col" style="width: 5%"><fmt:message
                                    key="cart.price.field"/></th>
                            <th scope="col" style="width: 5%"><fmt:message
                                    key="cart.quantity.field"/></th>
                            <th style="width: 2%"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="cart" items="${cart}" varStatus="i">
                            <tr>
                                <td>${cart.key.author}</td>
                                <td>${cart.key.title}</td>
                                <td>${cart.key.price} <fmt:message key="book.details.currency"/></td>
                                <td><input class="form-control" value="${cart.value}"
                                           name="quantity${i.index}"
                                           autocomplete="off" pattern="\d*[1-9]\d*" minlength="1" maxlength="2"/>
                                </td>
                                <td>
                                    <a href="${path}/do/?action=delete-book-from-cart&book=${cart.key.id}">x</a>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td style="visibility: collapse"></td>
                            <td><b>Total cost :</b></td>
                            <td>${totalCost} <fmt:message key="book.details.currency"/></td>
                        </tr>
                        <tr>
                            <td style="visibility: collapse"></td>
                            <td style="visibility: collapse"></td>
                            <td>
                                <button class="btn btn-outline-primary" type="submit" name="order"
                                        onclick="return onOrder();"><fmt:message
                                        key="cart.button.message"/>
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <p><button class="btn btn-outline-primary" name="refresh"
                                onclick="return onRefresh();">
                            &#8635;
                        </button></p>
                </form>
            </div>
        </div>
    </jsp:body>
</t:snippet>

<%--JavaScript to upload info from the same form by two actions--%>
<script language="Javascript">
    function onOrder() {
        document.cartForm.action = "/do/?action=order-books";
        document.cartForm.submit();
        return true;
    }

    function onRefresh() {
        document.cartForm.action = "/do/?action=refresh-cart";
        document.cartForm.submit();
        return true;
    }
</script>