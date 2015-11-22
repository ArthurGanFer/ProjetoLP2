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
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
    </head>
    <body>
        <form action="FrontController" method="POST" enctype="multipart/form-data">
            <fieldset>
                <legend>SIGN UP</legend>
                <label>Username: <input type="text" id="username" name="username" maxlength="20" required> *</label>
                <label>Password: <input type="password" id="password" name="password" maxlength="7" required> *</label>
                <label>Confirm Password: <input type="password" id="password2" name="password2" maxlength="7" required> *</label>
                <label>Nome: <input type="text" id="nome" name="nome" required></label>
                <label>E-mail: <input type="text" id="email" name="email" required></label>
                <label>Data de Nacimento: <input type="date" id="birthday" name="birthday"/></label>
                <label>Photo: <input type="file" id="file" name="file"/></label>
                <label><input type="submit" value="SIGN UP"/></label>
                <input type="hidden" name="command" value="user.insert"/>
                <a href="index.jsp">voltar</a>
            </fieldset>
        </form>
    </body>
</html>
