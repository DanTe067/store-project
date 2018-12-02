<%--
  Created by IntelliJ IDEA.
  User: Богдан
  Date: 27.11.2018
  Time: 17:07
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
url(https://static.gamespot.com/uploads/original/1585/15855271/3397664-star-wars-battlefront-ii_1510128919158.jpg) fixed center;
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
    <div class="row" style="margin-top: 15%; margin-bottom: 10%;">
        <div class="col-lg-4 col-md-4 col-lg-offset-4 col-md-offset-4">
            <img src="https://images-platform.99static.com/DqFkFeOyq7qmDBYP1QOfFOqMxxc=/fit-in/900x675/99designs-contests-attachments/49/49133/attachment_49133556"
                 style="margin-left: 55px;">
            <c:choose>
                <c:when test="${result == null}">
                    <div class="panel panel-secondary">
                        <div class="panel-heading">
                            <h4 align="center">Game #${game.gameId}</h4>
                        </div>
                        <div class="panel-body">
                            <form action="/profile" method="post">
                                <fieldset>
                                    <label for="jedi">Jedi:</label>
                                    <input type="text" size="20" class="form-control" id="jedi"
                                           name="jedi" value="${game.jedi.stat.rank} ${game.jedi.username}" disabled>
                                    <br>
                                    <h3 align="center">VS</h3>
                                    <br>
                                    <label for="sith">Sith:</label>
                                    <input type="text" size="20" class="form-control" id="sith"
                                           name="sith" value="${game.sith.stat.rank} ${game.sith.username}" disabled>
                                    <br>
                                    <label for="bet">Bet:</label>
                                    <input type="number" class="form-control" id="bet"
                                           name="bet" value="${game.bet}" disabled>
                                    <br>
                                    <button type="submit" class="btn btn-success btn-block">
                                        FLIP
                                    </button>
                                    <br>
                                    <a href="/main?dismissGame=${game.gameId}">
                                        <button type="button" class="btn btn-danger btn-block">
                                            Dismiss game
                                        </button>
                                    </a>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </c:when>
                <c:when test="${result != null}">
                    <img src="${resultCoin}"
                         align="center">
                    <div class="panel panel-info col-lg-6 col-md-6 col-lg-offset-4 col-md-offset-4">
                        <div class="panel-heading">
                            <h4 align="center">Result</h4>
                        </div>
                        <div class="panel-body">
                            <form action="/fraction" method="post">
                                <fieldset>
                                    <label for="result">Winner:</label>
                                    <input align="center" type="text" readonly class="form-control" id="result"
                                           name="result" value="${result.winner.stat.rank} ${result.winner.username}"
                                           disabled>
                                    <br>
                                    <a href="/main">
                                        <button type="button" class="btn btn-success btn-block">
                                            Return
                                        </button>
                                    </a>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
