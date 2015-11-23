<%-- 
    Document   : index
    Created on : 30/09/2015, 07:48:52
    Author     : 1147106
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projeto LP2</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="css/singin.css"/>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
    </head>
    <body>
        <div class="container">
            <form class="form-signin" action="FrontController" method="POST">
                <h2 class="form-signin-heading">LOGIN</h2>
                <label for="username" class="sr-only">Username</label>
                <input type="text" id="username" name="username" class="form-control" placeholder="Username" required>
                <label for="password" class="sr-only">Password</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="password" required>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="lembrar">
                        Lembrar Senha
                    </label>
                </div>
                <input type="hidden" name="command" value="user.login"/>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
                <a href="register.jsp">SIGN UP</a>
            </form>
        </div>
    </body>
</html>