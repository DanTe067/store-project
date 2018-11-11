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
</head>
<body style="background-image:url(http://2.bp.blogspot.com/-v8fxIAZrrSM/UiAUGi3AneI/AAAAAAAAJXI/jCbg3AGbe24/s1600/Background-Star-Wars-Wallpapers3.jpg); background-position: center; background-size: cover;">
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
                        <input type="text" class="form-control" placeholder="Choose username.." id="username"
                               name="username" required>
                        <br>
                        <label for="password">Password:</label>
                        <input type="password" class="form-control" placeholder="Choose password.." id="password"
                               name="password" required>
                        <br>
                        <label for="email">E-mail:</label>
                        <input type="email" class="form-control" placeholder="Enter your email.." id="email"
                               name="email" required>
                        <br>
                        <button type="submit" class="btn btn-success btn-block">
                            Sign Up
                        </button>
                        <br>
                        <a href="/signup?cancel=true">
                            <button type="button" class="btn btn-danger btn-block">
                                Cancel
                            </button>
                        </a>
                        <c:choose>
                            <c:when test="${error != null}">
                                <div class="panel panel-danger">
                                    <div class="panel-heading">ERROR</div>
                                    <div class="panel-body">
                                        <p style="color: red" align="center">${error}</p>
                                    </div>
                                </div>
                            </c:when>
                        </c:choose>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
