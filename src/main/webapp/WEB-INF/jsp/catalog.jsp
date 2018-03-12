<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="lang"/>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<fmt:message key="catalog.title" var="title"/>
<c:set var="pageCount" value="${pageCount}"/>
<c:set var="books" value="${books}"/>
<fmt:message key="catalog.search.button.placeholder" var="catalogSearchButtonPlaceholder"/>

<t:snippet title="${title}">
    <jsp:body>
        <div style="width: 20%; height: 80%; float:left; margin-top: 100px;">
            <form class="form-inline my-2 my-lg-0" style="margin-left: 50px;"
                  action="${path}/do/?action=show-catalog-page&page=1" method="post">
                <input class="form-control mr-sm-2" type="text"
                       placeholder="${catalogSearchButtonPlaceholder}"
                       aria-label="Search"
                       name="search_input">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
                    <fmt:message key="catalog.search.button.message"/>
                </button>
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
                            <td>${book.price} <fmt:message key="book.details.currency"/></td>
                            <td width="2%"><a class="button"
                                              href="${path}/do/?action=show-book-details&id=${book.id}">&#62;</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

                <%--Pagination--%>
            <nav aria-label="...">
                <c:if test="${pageCount != 1}">
                    <ul class="pagination pagination-sm">
                        <c:forEach var="i" begin="${1}" end="${pageCount}">
                            <li class="page-item"><a class="page-link"
                                                     href="${path}/do/?action=show-catalog-page&page=${i}"
                                                     class="link-style">${i}</a></li>
                        </c:forEach>
                    </ul>
                </c:if>
            </nav>
        </div>
    </jsp:body>
</t:snippet>