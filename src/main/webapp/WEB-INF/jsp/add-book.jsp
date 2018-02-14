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
        <div style="margin-top: 60px; margin-left: 60px; width: 60%">
            <form method="post" action="/do/?action=add-book">
                <h2>Title:</h2>
                <h2><b><input name="title"></b></h2>

                <h4>Author:</h4>
                <h4><b><input name="author"></b></h4>

                Price:
                <b><input name="price"></b>

                <p>isbn:
                    <b><input name="isbn""></b>
                </p>
                <p><b>Description:</b>
                </p><input type="text" name="description" style="width: 400px">

                <p><b>Quantity:</b></p>
                <b><input name="quantity"></b>
                <br>
                <button type="submit" class="btn btn-outline-primary">
                    Submit
                </button>
            </form>
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
