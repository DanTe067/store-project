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
        url(${mainBackground}) fixed center;
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
    <div class="row" style="margin-top: 5%; margin-bottom: 5%;">
        <div class="col-lg-8 col-md-8 col-lg-offset-4 col-md-offset-4">
            <div class="card text-center col-lg-6 col-md-6" align="center">
                <img class="card-img"
                     src="https://http2.mlstatic.com/taza-magica-personalizada-jedi-sith-star-wars-D_NQ_NP_461415-MLA25237165774_122016-F.jpg"
                     alt="Card image" height="324" width="700"
                     style="border-radius: 25%; border: 1px solid #20699A; padding: 5px; position: relative; display: block; margin-left: -170px;">
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
    <div class="row" style="margin-bottom: 5%;">
        <div class="col-lg-12 col-md-12">
            <hr>
            <h2 style="color: white; text-align: center;">PvP rooms</h2>
            <hr>
            <c:choose>
                <c:when test="${currentGame == null}">
                    <button type="button" class="btn btn-primary btn-block" data-toggle="modal"
                            data-target="#modal">
                        Create room
                    </button>
                </c:when>
                <c:when test="${currentGame != null}">
                    <br>
                    <a href="/game?gameId=${currentGame.gameId}">
                        <button type="button" class="btn btn-primary btn-block">
                            Current game
                        </button>
                    </a>
                </c:when>
            </c:choose>
            <c:if test="${error != null}">
                <br>
                <div class="panel panel-danger">
                    <div class="panel-heading">ERROR</div>
                    <div class="panel-body">
                        <p style="color: red" align="center">${error}</p>
                    </div>
                </div>
            </c:if>
            <br>
            <%--Modal--%>
            <div id="modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">New PvP room</h4>
                        </div>
                        <div class="modal-body">
                            <div class="panel panel-primary">
                                <div class="panel-heading">Choose your side and bet:
                                </div>
                                <div class="panel-body">
                                    <form action="/main" method="post">
                                        <fieldset>
                                            <label>Side:</label><br>
                                            <label class="radio-inline"><input type="radio" name="side" value="jedi">Jedi</label>
                                            <label class="radio-inline"><input type="radio" name="side" value="sith">Sith</label>
                                            <br>
                                            <label for="bet">Bet:</label>
                                            <input type="number" class="form-control" id="bet"
                                                   name="bet">
                                            <br>
                                        </fieldset>
                                        <button type="submit" class="btn btn-success form-control">Create
                                        </button>
                                        <br><br>
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
            <c:forEach var="game" items="${availableGames}">
                <div class="card text-center col-lg-3 col-md-3" style="padding-bottom: 2%;">
                    <img class="card-img"
                         src="https://pre00.deviantart.net/63e4/th/pre/i/2015/319/2/a/republic_vs_empire_by_eiluvision-d9guzr1.png"
                         alt="Card image" height="200" width="200"
                         style="padding: 5px; display: block; margin-left: 25px;">
                    <h5 class="card-title" style="color: white"><b>Enter room</b></h5>
                    <div class="card-body" style="color: white; text-align: center">
                        <c:choose>
                            <c:when test="${game.jedi == null}">
                                ${game.bet}<br>
                                VS<br>
                                ${game.sith.stat.rank} <c:out value="${game.sith.username}"/>
                                <br>
                                <c:if test="${currentGame == null}">
                                    <div class="btn-group btn-lg">
                                        <button type="button" class="btn btn-primary"><a
                                                href="/game?gameId=${game.gameId}"
                                                style="text-decoration: none; color: white;">Join
                                            battle as JEDI</a>
                                        </button>
                                    </div>
                                </c:if>
                            </c:when>
                            <c:when test="${game.sith == null}">
                                VS<br>
                                ${game.jedi.stat.rank} <c:out value="${game.jedi.username}"/>
                                <br>
                                <c:if test="${currentGame == null}">
                                    <div class="btn-group btn-lg">
                                        <button type="button" class="btn btn-primary"><a
                                                href="/game?gameId=${game.gameId}"
                                                style="text-decoration: none; color: white;">Join
                                            battle as SITH</a>
                                        </button>
                                    </div>
                                </c:if>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>
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
