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
<c:set var="pageCount" value="${pageCount}"/>
<c:set var="currentPage" value="${currentPage}"/>
<c:set var="books" value="${books}"/>


<t:snippet title="${title}">
    <jsp:body>
        <div style="width: 20%; height: 80%; float:left; margin-top: 100px;">
            <form class="form-inline my-2 my-lg-0" style="margin-left: 50px;"
                  action="/do/?action=show-catalog-page&page=1" method="post">
                <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search"
                       name="search_input">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
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
                    <c:forEach var="book" items="${books}">
                        <tr>
                            <td>${book.author}</td>
                            <td>${book.title}</td>
                            <td>${book.price}</td>
                            <td><a href="/do/?action=show-detailed-book-info&id=${book.id}">
                                details</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

                <%--Very ugly way to create a pagination bar--%>
            <nav aria-label="...">
                <c:if test="${pageCount != 1}">
                    <ul class="pagination pagination-sm">
                        <c:forEach var="i" begin="${1}" end="${pageCount}">
                            <li class="page-item"><a class="page-link" href="/do/?action=show-catalog-page&search=${search}&page=${i}"
                                                     class="link-style">${i}</a></li>
                        </c:forEach>
                    </ul>
                </c:if>
            </nav>
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