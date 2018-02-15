<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="/WEB-INF/css/jumbotron.css"/>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css">
<fmt:setBundle basename="lang"/>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<fmt:message key="customer.orders.title" var="title"/>

<c:set var="pageCount" value="${pageCount}"/>
<c:set var="currentPage" value="${currentPage}"/>
<c:set var="orders" value="${orders}"/>

<t:snippet title="${title}">
    <jsp:body>
        <div class="container" style="margin-top: 80px">
            <c:if test="${fn:length(orders) != 0}">
                <form name="Form1" method="post" action="/do/?action=update-operator-orders">

                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col"><fmt:message key="customer.orders.id.field"/></th>
                            <th scope="col"><fmt:message key="customer.orders.total.price.field"/></th>
                            <th scope="col"><fmt:message key="customer.orders.status.field"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="order" items="${orders}" varStatus="i">
                            <tr>
                                <td>${order.id}</td>
                                <td>${order.totalPrice}</td>
                                <td>
                                    <c:if test="${order.status == 'waiting'}">
                                        <select name="${order.id}">
                                            <option selected value="waiting">waiting</option>
                                            <option value="ready">ready</option>
                                            <option value="completed">completed</option>
                                            <option value="cancelled">cancelled</option>
                                        </select>
                                    </c:if>
                                    <c:if test="${order.status == 'ready'}">
                                        <select name="${order.id}">
                                            <option value="waiting">waiting</option>
                                            <option selected value="ready">ready</option>
                                            <option value="completed">completed</option>
                                            <option value="cancelled">cancelled</option>
                                        </select>
                                    </c:if>
                                    <c:if test="${order.status == 'completed'}">
                                        <select name="${order.id}">
                                            <option value="waiting">waiting</option>
                                            <option value="ready">ready</option>
                                            <option selected value="completed">completed</option>
                                            <option value="cancelled">cancelled</option>
                                        </select>
                                    </c:if>
                                    <c:if test="${order.status == 'cancelled'}">
                                        <select name="${order.id}">
                                            <option value="waiting">waiting</option>
                                            <option value="ready">ready</option>
                                            <option value="completed">completed</option>
                                            <option selected value="cancelled">cancelled</option>
                                        </select>
                                    </c:if>

                                </td>
                                <td width="2%">
                                    <button class="btn btn-outline-primary" name="details" type="submit"
                                            onclick="return onDetails(${order.id});">
                                        &#62;
                                    </button>
                                </td>
                                <td width="2%">
                                    <button class="btn btn-outline-primary" name="refresh" type="submit"
                                            onclick="return onRefresh(${order.id});">
                                        &#8635;
                                    </button>
                                </td>
                                <td width="2%">
                                    <button class="btn btn-outline-primary" name="delete" type="submit"
                                            onclick="return onDelete(${order.id});">
                                        &#215;
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>
            </c:if>

                <%--Pagination bar--%>
            <nav aria-label="...">
                <c:if test="${pageCount != 1}">
                    <ul class="pagination pagination-sm">
                        <c:forEach var="i" begin="${1}" end="${pageCount}">
                            <li class="page-item"><a class="page-link"
                                                     href="/do/?action=show-operator-orders-page&page=${i}"
                                                     class="link-style">${i}</a></li>
                        </c:forEach>
                    </ul>
                </c:if>
            </nav>
        </div>

        <c:if test="${fn:length(orders) == 0}">
            <p style="margin-top: 150px; text-align: center"> Order list is empty </p>
        </c:if>


    </jsp:body>
</t:snippet>


<script language="Javascript">
    function onRefresh(id) {
        document.Form1.action = "/do/?action=update-operator-orders&id=" + id;
        document.Form1.submit();             // Submit the page
        return true;
    }

    function onDetails(id) {
        document.Form1.action = "/do/?action=show-order-details&id=" + id;
        document.Form1.submit();             // Submit the page
        return true;
    }

    function onDelete(id) {
        document.Form1.action = "/do/?action=delete-order&id=" + id;
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