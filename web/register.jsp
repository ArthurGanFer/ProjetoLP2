<%-- 
    Document   : register
    Created on : 01/10/2015, 07:49:00
    Author     : 1147106
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projeto LP2</title>
    </head>
    <body>
        <form action="FrontController" method="POST" enctype="multipart/form-data">
            <fieldset>
                <legend>SIGN UP</legend>
                <p>Username: <input type="text" name="username" maxlength="20" required> *</p>
                <p>Password: <input type="password" name="password" maxlength="7" required> *</p>
                <p>Confirm Password: <input type="password" name="password2" maxlength="7" required> *</p>
                <p>Nome <input type="text" name="nome" required></p>
                <p>E-mail <input type="text" name="email" required></p>
                <!-- COLOCAR AQUI OS OUTROS CAMPOS-->
                <p><input type="date" name="birthday"/></p>
                <p>Photo: <input type="file" name="file"/></p>
                <p><input type="submit" value="SIGN UP"/></p>
                <input type="hidden" name="command" value="user.insert"/>
            </fieldset>
        </form>
    </body>
</html>
