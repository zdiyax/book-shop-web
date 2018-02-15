<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="/WEB-INF/css/personalinfo.css"/>
<fmt:setBundle basename="lang"/>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<fmt:message key="profile.title" var="title"/>
<fmt:message key="personal.info.full_name.placeholder" var="personalInfoFullNamePlaceholder"/>
<fmt:message key="personal.info.email.placeholder" var="personalInfoEmailPlaceholder"/>
<fmt:message key="personal.info.address.placeholder" var="personalInfoAddressPlaceholder"/>
<fmt:message key="personal.info.telephone_number.placeholder" var="personalInfoTelephoneNumberPlaceholder"/>

<t:snippet title="${title}">
    <jsp:body>
        <div id="fullscreen_bg" class="fullscreen_bg">
        <form class="form-signin" action="${path}/do/?action=update-personal-info" method="post">
            <div class="container" style="margin-top: 60px">
                <div class="row">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="panel panel-default">
                            <div class="panel panel-primary">
                                <div class="panel-body">
                                    <h3 class="text-center">
                                        <fmt:message key="personal.info.message"/></h3>
                                    <div class="form-group">
                                        <div class="input-group">
                            <span class="input-group-addon">
                            </span>
                                            <input type="text" class="form-control" name="fullName"
                                                   placeholder="${personalInfoFullNamePlaceholder}"
                                                   value="${user.fullName}" autocomplete="off" minlength="6"
                                                   maxlength="32"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"></span>
                                            <input type="text" class="form-control" name="address"
                                                   placeholder="${personalInfoAddressPlaceholder}"
                                                   value="${user.address}" autocomplete="off" minlength="6"
                                                   maxlength="64"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"></span>
                                            <input type="email" class="form-control" name="email"
                                                   placeholder="${personalInfoEmailPlaceholder}" value="${user.email}"
                                                   autocomplete="off" minlength="6" maxlength="32"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"></span>
                                            <input type="tel" class="form-control" name="telephoneNumber"
                                                   placeholder="${personalInfoTelephoneNumberPlaceholder}"
                                                   value="${user.telephoneNumber}" autocomplete="off" minlength="6"
                                                   maxlength="32"/>
                                        </div>
                                    </div>
                                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                                        <fmt:message key="personal.info.button"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </jsp:body>
</t:snippet>
