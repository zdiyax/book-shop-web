<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" type="text/css" href="/css/header.css" />
<c:set var="prefix" value="${pageContext.request.contextPath}"/>

<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>

</head>

<body>

<header class="header-fixed">

    <div class="header-limiter">

        <h1><a href="">Book<span>Geek</span></a></h1>

        <nav>
            <a href="${prefix}/do/action?show-home-page">Home</a>
            <a href="${prefix}/do/action?show-catalog-page">Catalog</a>
            <a href="${prefix}/do/action?show-about-page">About</a>
            <a href="${prefix}/do/action?show-login-page">Login</a>
        </nav>

    </div>

</header>

<div class="header-fixed-placeholder"></div>



<div class="menu">

</div>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script>
    $(document).ready(function(){
        var showHeaderAt = 150;
        var win = $(window),
            body = $('body');
        // Show the fixed header only on larger screen devices
        if(win.width() > 600){
            // When we scroll more than 150px down, we set the
            // "fixed" class on the body element.
            win.on('scroll', function(e){
                if(win.scrollTop() > showHeaderAt) {
                    body.addClass('fixed');
                }
                else {
                    body.removeClass('fixed');
                }
            });
        }
    });
</script>

</body>

</html>