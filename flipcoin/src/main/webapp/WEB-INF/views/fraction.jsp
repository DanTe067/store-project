<%--
  Created by IntelliJ IDEA.
  User: Богдан
  Date: 20.11.2018
  Time: 15:43
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
        url(${fractionalBackground}) fixed center;
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
    <div class="row"
            <c:choose>
                <c:when test="${side == 'jedi'}">
                    style="margin-top: 50%; margin-bottom: 5%;"
                </c:when>
                <c:when test="${side == 'sith'}">
                    style="margin-top: 10%; margin-bottom: 5%;"
                </c:when>
            </c:choose>
    >
        <div class="panel panel-info col-lg-4 col-md-4 col-lg-offset-4 col-md-offset-4">
            <div class="panel-heading">
                <h4 align="center">BattleField</h4>
            </div>
            <div class="panel-body">
                <form action="/fraction" method="post">
                    <fieldset>
                        <label for="result">Report:</label>
                        <input align="center" type="text" readonly class="form-control" id="result"
                               name="result" value="${result}" disabled>
                        <br>
                        <label for="bet">Bet:</label>
                        <input type="number" size="10" class="form-control" id="bet"
                               name="bet" value="10" required>
                        <br>
                        <button type="submit" class="btn btn-success btn-block">
                            Join battle
                        </button>
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
