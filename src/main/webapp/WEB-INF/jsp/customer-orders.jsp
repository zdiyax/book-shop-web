<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="lang"/>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<fmt:message key="customer.orders.title" var="title"/>
<c:set var="orders" value="${orders}"/>

<t:snippet title="${title}">
    <jsp:body>
        <div class="container" style="margin-top: 150px">
            <c:if test="${fn:length(orders) == 0}">
                <p style="margin-top: 150px; text-align: center"> Order list is empty </p>
            </c:if>
            <c:if test="${fn:length(orders) != 0}">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="customer.orders.id.field"/></th>
                        <th scope="col"><fmt:message key="customer.orders.total.price.field"/></th>
                        <th scope="col"><fmt:message key="customer.orders.status.field"/></th>
                        <th><fmt:message key="customer.orders.details.field"/></th>
                        <th><fmt:message key="customer.orders.cancel.order.field"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.totalPrice}</td>
                            <td>${order.status}</td>
                            <td width="2%">
                                <button class="btn btn-outline-primary" name="details" type="submit">
                                    <a href="${path}/do/?action=show-order-details&id=${order.id}">&#62;</a>
                                </button>
                            </td>
                            <td width="2%">
                                <button class="btn btn-outline-primary" name="refresh" type="submit">
                                    <a href="${path}/do/?action=cancel-order&id=${order.id}">&#215;</a>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </jsp:body>
</t:snippet>