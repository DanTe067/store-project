<%--
  Created by IntelliJ IDEA.
  User: Богдан
  Date: 20.11.2018
  Time: 17:00
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
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-show-password/1.0.3/bootstrap-show-password.min.js"></script>
</head>
<body style="background:
url(https://d2v9y0dukr6mq2.cloudfront.net/video/thumbnail/moving-through-stars-in-space_-1zccenlb__F0000.png) fixed center;
background-size: cover;">
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
                            <span class="glyphicon glyphicon-user"></span> Welcome, ${user.stat.rank} <c:out
                                value="${user.username}"/>
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
    <div class="row" style="margin-top: 10%; margin-bottom: 10%;">
        <div class="panel panel-info col-lg-4 col-md-4 col-lg-offset-4 col-md-offset-4">
            <div class="panel-heading">
                <h4 align="center">Your credentials:</h4>
            </div>
            <div class="panel-body">
                <form action="/profile" method="post">
                    <fieldset>
                        <label for="username">Username:</label>
                        <input type="text" size="20" class="form-control" id="username"
                               name="username" value="${user.username}" disabled>
                        <br>
                        <label for="password">Password:</label>
                        <input type="password" size="20" class="form-control" id="password"
                               name="password" value="${user.password}" disabled>
                        <br>
                        <label for="email">E-mail:</label>
                        <input type="email" size="50" class="form-control" id="email"
                               name="email" value="${user.email}" disabled>
                        <br>
                        <label for="account">Account:</label>
                        <input type="number" class="form-control" id="account"
                               name="account" value="${user.stat.account}" disabled>
                        <br>
                        <label for="score">Score:</label>
                        <input type="number" class="form-control" id="score"
                               name="score" value="${user.stat.score}" disabled>
                        <br>
                        <label for="rank">Rank:</label>
                        <input type="text" class="form-control" id="rank"
                               name="rank" value="${user.stat.rank}" disabled>
                        <br>
                        <button type="button" class="btn btn-primary btn-block" data-toggle="modal"
                                data-target="#modal">
                            Refresh stats
                        </button>
                        <%--Modal--%>
                        <div id="modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Refreshing stats</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="panel panel-primary">
                                            <div class="panel-heading">Are you sure about that? All data will be lost
                                                include your score, rank and account:
                                            </div>
                                            <div class="panel-body">
                                                <form action="/profile" method="post">
                                                    <button type="submit" class="btn btn-success form-control">Refresh
                                                    </button>
                                                    <br>
                                                    <div class="form-group">
                                                        <button type="button" class="btn btn-danger form-control"
                                                                data-dismiss="modal">
                                                            Cancel
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%--Modal--%>
                        <c:choose>
                            <c:when test="${error != null}">
                                <br>
                                <div class="panel panel-danger">
                                    <div class="panel-heading">ERROR</div>
                                    <div class="panel-body">
                                        <p style="color: red" align="center">${error}</p>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${success != null}">
                                <br>
                                <div class="panel panel-success">
                                    <div class="panel-heading">SUCCESS</div>
                                    <div class="panel-body">
                                        <p style="color: green" align="center">${success}</p>
                                    </div>
                                </div>
                            </c:when>
                        </c:choose>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
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