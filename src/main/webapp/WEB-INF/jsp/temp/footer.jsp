<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="/css/footer.css" >
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
<link href="http://fonts.googleapis.com/css?family=Cookie" rel="stylesheet" type="text/css">
<c:set var="prefix" value="${pageContext.request.contextPath}"/>
<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="footer, address, phone, icons" />

</head>

<body>
<!-- Content -->

</body>


<footer class="footer-distributed">

    <div class="footer-left">

        <h3>Book<span>Geek</span></h3>

        <p class="footer-links">
            <a href="${prefix}/do/action?show-home-page">Home</a>
            ·
            <a href="${prefix}/do/action?show-catalog-page">Catalog</a>
            ·
            <a href="${prefix}/do/action?show-about-page">About</a>
        </p>

        <p class="footer-company-name">Bookshop &copy; 2017</p>
    </div>

    <div class="footer-center">

        <div>
            <i class="fa fa-map-marker"></i>
            <p><span>7 Qurylysshylar Avenue</span> Qaraghandy, Qazaqstan</p>
        </div>

        <div>
            <i class="fa fa-phone"></i>
            <p>+7 (700) 149 11 64</p>
        </div>

        <div>
            <i class="fa fa-envelope"></i>
            <p><a href="mailto:support@bookgeek.kz">support@bookgeek.kz</a></p>
        </div>

    </div>

    <div class="footer-right">

        <p class="footer-company-about">
            <span>About the company</span>
            Books for everyone! Enjoy your best deals!
        </p>
        <%--remove footer icons boxes--%>
        <div class="footer-icons">
            <a href="#"><i class="fa fa-facebook"></i></a>
            <a href="https://vk.com/tatikara"><i class="fa fa-vk"></i></a>
            <a href="https://github.com/zhannur-diyas"><i class="fa fa-github"></i></a>
        </div>

    </div>

</footer>


</html>