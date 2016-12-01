<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 12/1/2016
  Time: 9:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/css/header.css">

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Basic Header</title>

    <link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>

</head>

<body>

<header class="header-fixed">

    <div class="header-limiter">

        <h1><a href="#">Book<span>shop</span></a></h1>

        <nav>
            <a href="../WEB-INF/jsp/index.jsp">Home</a>
            <a href="catalog.jsp">Catalog</a>
            <a href="#">About</a>
            <a href="#">Contact</a>
        </nav>

    </div>

</header>

<div class="header-fixed-placeholder"></div>



<div class="menu">

    <img src="resources/arrow.png" alt="arrow" height="30">

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

