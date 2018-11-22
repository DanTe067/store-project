<%--
  Created by IntelliJ IDEA.
  User: Богдан
  Date: 14.11.2018
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Flip Side</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background:
url(https://d2v9y0dukr6mq2.cloudfront.net/video/thumbnail/moving-through-stars-in-space_-1zccenlb__F0000.png) fixed center;
background-size: cover;">
<%--jedi https://cdn.hipwallpaper.com/i/91/42/k4fDga.jpg
    sith https://wonderfulengineering.com/wp-content/uploads/2014/04/space-wallpapers-20.jpg
--%>
<div class="container">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/main"><b>FLIP SIDE</b></a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="/main">Game rooms</a></li>
                <c:if test="${user.role.name == 'admin'}">
                    <li><a href="/results">PvP results</a></li>
                </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAuthenticated()">
                    <li>
                        <a href="/profile">
                            <span class="glyphicon glyphicon-user"></span> Welcome, ${user.stat.rank} ${user.username}
                        </a>
                    </li>
                    <li>
                        <a href="/logout">
                            <span class="glyphicon glyphicon-log-in"></span>
                        </a>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </nav>
    <div class="row" style="margin-top: 5%; margin-bottom: 5%;">
        <div class="col-lg-8 col-md-8 col-lg-offset-4 col-md-offset-4">
            <div class="card text-center col-lg-6 col-md-6" align="center">
                <img class="card-img"
                     src="https://http2.mlstatic.com/taza-magica-personalizada-jedi-sith-star-wars-D_NQ_NP_461415-MLA25237165774_122016-F.jpg"
                     alt="Card image" height="324" width="700"
                     style="border-radius: 25%; border: 1px solid #20699A; padding: 5px; position: relative; display: block;">
                <h5 class="card-title" style="color: white"><b>Choose your side</b></h5>
                <div class="card-body">
                    <div class="btn-group btn-lg">
                        <button type="button" class="btn btn-primary"><a href="/fraction?side=jedi"
                                                                         style="text-decoration: none; color: white;">Join
                            the rebellion</a>
                        </button>
                        <button type="button" class="btn btn-danger"><a href="/fraction?side=sith"
                                                                        style="text-decoration: none; color: white;">Join
                            the empire</a>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <hr>
    <nav class="navbar navbar-inverse navbar-fixed-bottom">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Creator: </a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="https://www.linkedin.com/in/богдан-тесля-597901149/" target="_blank"><img
                        src="https://www.freeiconspng.com/uploads/linkedin-icon-25.png" height="20" width="20"/></a>
                </li>
                <li><a href="https://github.com/DanTe067" target="_blank"><img
                        src="https://mbtskoudsalg.com/images/github-icon-png-7.png" height="20" width="20"/></a>
                </li>
            </ul>
        </div>
    </nav>
</div>
</body>
</html>
