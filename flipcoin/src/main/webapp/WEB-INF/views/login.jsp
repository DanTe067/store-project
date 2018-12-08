<%--
  Created by IntelliJ IDEA.
  User: Богдан
  Date: 11.11.2018
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Flip Side</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background:
url(http://2.bp.blogspot.com/-v8fxIAZrrSM/UiAUGi3AneI/AAAAAAAAJXI/jCbg3AGbe24/s1600/Background-Star-Wars-Wallpapers3.jpg) center;
background-size: cover;">
<div class="container">
    <br><br><br><br>
    <div class="row">
        <div class="panel panel-info col-lg-4 col-md-4 col-lg-offset-4 col-md-offset-4">
            <div class="panel-heading">
                <h4 align="center">FLIP SIDE</h4>
            </div>
            <div class="panel-body">
                <form accept-charset="UTF-8" role="form" action="/login" method="post">
                    <fieldset>
                        <label for="username"><b>Username</b></label>
                        <input type="text" size="20" class="form-control" placeholder="Enter username.." id="username"
                               name="username"
                               required>
                        <br>
                        <label for="password"><b>Password</b></label>
                        <input type="password" size="20" class="form-control" placeholder="Enter password.."
                               id="password"
                               name="password" required>
                        <br>
                        <c:choose>
                            <c:when test="${param.error != null}">
                                <div class="panel panel-danger">
                                    <div class="panel-heading">ERROR</div>
                                    <div class="panel-body">
                                        <p style="color: red" align="center">Wrong login or password. Make sure
                                            caps-lock is turned off</p>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${success != null}">
                                <div class="panel panel-success">
                                    <div class="panel-heading">SUCCESS</div>
                                    <div class="panel-body">
                                        <p style="color: green" align="center">${success}</p>
                                    </div>
                                </div>
                            </c:when>
                        </c:choose>
                        <button type="submit" class="btn btn-success btn-block">
                            Log in
                        </button>
                        <br>
                        <a href="/signup">
                            <button type="button" class="btn btn-primary btn-block">
                                Sign up
                            </button>
                        </a>
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