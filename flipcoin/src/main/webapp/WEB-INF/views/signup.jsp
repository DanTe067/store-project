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
                <form action="/signup" method="post">
                    <fieldset>
                        <label for="username">Username:</label>
                        <input type="text" size="20" class="form-control" placeholder="Choose username.." id="username"
                               name="username" required>
                        <br>
                        <label for="password">Password:</label>
                        <input type="password" size="20" class="form-control" placeholder="Choose password.."
                               id="password"
                               name="password" required data-toggle="password">
                        <br>
                        <label for="email">E-mail:</label>
                        <input type="email" size="50" class="form-control" placeholder="Enter your email.." id="email"
                               name="email">
                        <br>
                        <button type="submit" class="btn btn-success btn-block">
                            Sign Up
                        </button>
                        <br>
                        <a href="/login">
                            <button type="button" class="btn btn-danger btn-block">
                                Cancel
                            </button>
                        </a>
                        <c:if test="${error != null}">
                            <div class="panel panel-danger">
                                <div class="panel-heading">ERROR</div>
                                <div class="panel-body">
                                    <p style="color: red" align="center">${error}</p>
                                </div>
                            </div>
                        </c:if>
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
