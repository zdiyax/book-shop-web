<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="lang"/>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<fmt:message key="order.details.title" var="title"/>
<c:set var="details" value="${details}"/>

<t:snippet title="${title}">
    <jsp:body>
        <div style="width: 20%; height: 80%; float:left; margin-top: 100px;"></div>
        <div style="width: 80%; height: 80%; float:right; margin-top: 100px;">
            <div class="container">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="order.details.author.field"/></th>
                        <th scope="col"><fmt:message key="order.details.title.field"/></th>
                        <th scope="col"><fmt:message key="order.details.quantity.field"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="detail" items="${details}">
                        <tr>
                            <td>${detail.bookAuthor}</td>
                            <td>${detail.bookTitle}</td>
                            <td>${detail.quantity}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td style="visibility: collapse"></td>
                        <td><b><fmt:message key="order.details.total.price.field"/></b></td>
                        <td><b>${details.get(0).orderPrice}</b></td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </jsp:body>
</t:snippet>