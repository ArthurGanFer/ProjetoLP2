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
        <link rel="stylesheet" type="text/css" href="css/mainStyle.css"/>
    </head>
    <body>
        <form action="FrontController" method="POST">
            <fieldset>
                <legend>LOGIN</legend>
                <label>Username: <input type="text" name="username" placeholder="username" required value="${cookie.name.value}"/> *</label>
                <label>Password: <input type="password" name="password" placeholder="password" required value="${cookie.pwd.value}"/> *</label>
                <label><input type="checkbox" name="lembrar"> Lembrar Senha</label>
                <label><input type="submit" value="LOGIN"/></label>
                <input type="hidden" name="command" value="user.login"/>
                <a href="register.jsp">SIGN UP</a>
            </fieldset>
        </form>
    </body>
</html>
