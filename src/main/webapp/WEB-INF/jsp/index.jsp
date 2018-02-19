<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<fmt:setBundle basename="lang"/>
<fmt:message key="home.title" var="title"/>

<t:snippet title="${title}">
    <jsp:body>
        <div class="jumbotron">
            <div class="container">
                <h1 class="display-3">book-shop-web</h1>
                <p><fmt:message key="home.description.message"/></p>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <h3><fmt:message key="home.contacts"/></h3>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <h2><fmt:message key="home.contacts.telephone"/></h2>
                    <p><fmt:message key="home.contacts.telephone.message"/></p>
                </div>
                <div class="col-md-4">
                    <h2><fmt:message key="home.contacts.email"/></h2>
                    <p><fmt:message key="home.contacts.email.message"/></p>
                </div>
                <div class="col-md-4">
                    <h2><fmt:message key="home.contacts.address"/></h2>
                    <p><fmt:message key="home.contacts.address.message"/></p>
                </div>
            </div>
        </div>
    </jsp:body>
</t:snippet>