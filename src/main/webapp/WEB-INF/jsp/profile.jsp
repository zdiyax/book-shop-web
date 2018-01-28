<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="/WEB-INF/css/jumbotron.css"/>
<link rel="stylesheet" type="text/css" href="/WEB-INF/css/personalinfo.css"/>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
      integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
<fmt:setBundle basename="lang"/>
<c:set var="prefix" value="${pageContext.request.contextPath}"/>
<fmt:message key="profile.title" var="title"/>

<t:snippet title="${title}">
    <jsp:body>
        <div id="fullscreen_bg" class="fullscreen_bg"/>
        <form class="form-signin">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="panel panel-default">
                            <div class="panel panel-primary">

                                <h3 class="text-center">
                                    Update my information</h3>

                                <div class="panel-body">

                                    <div class="form-group">
                                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span>
                            </span>
                                            <input type="text" class="form-control" placeholder="First Name"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"><span
                                                    class="glyphicon glyphicon-user"></span></span>
                                            <input type="text" class="form-control" placeholder="Last Name"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"><span
                                                    class="glyphicon glyphicon-envelope"></span></span>
                                            <input type="text" class="form-control" placeholder="Email"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"><span
                                                    class="glyphicon glyphicon-lock"></span></span>
                                            <input type="password" class="form-control" placeholder="New Password"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"><span
                                                    class="glyphicon glyphicon-home"></span></span>
                                            <input type="text" class="form-control" placeholder="Address"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"><span
                                                    class="glyphicon glyphicon-calendar"></span></span>
                                            <input type="text" class="form-control" placeholder="Age"/>
                                        </div>
                                    </div>
                                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                                        Save
                                    </button>


                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </form>


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
