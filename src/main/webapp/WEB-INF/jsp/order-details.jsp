<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/WEB-INF/css/jumbotron.css"/>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css">

<fmt:setBundle basename="lang"/>
<c:set var="prefix" value="${pageContext.request.contextPath}"/>
<fmt:message key="catalog.title" var="title"/>
<c:set var="details" value="${details}"/>


<t:snippet title="${title}">
    <jsp:body>
        <div style="width: 20%; height: 80%; float:left; margin-top: 100px;">
        </div>

        <div style="width: 80%; height: 80%; float:right; margin-top: 100px;">
            <div class="container">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="catalog.authorField"/></th>
                        <th scope="col"><fmt:message key="catalog.titleField"/></th>
                        <th scope="col"><fmt:message key="catalog.priceField"/></th>
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
                        <td>
                            Total Price:
                        </td>
                        <td>${details.get(0).orderPrice}</td>
                    </tr>
                    </tfoot>
                </table>
            </div>
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