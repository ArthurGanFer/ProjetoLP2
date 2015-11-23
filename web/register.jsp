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
        <link rel="stylesheet" type="text/css" href="css/singin.css"/>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
    </head>
    <body>
        <div class="container">
            <form class="form-signin" action="FrontController" method="POST" enctype="multipart/form-data">
                <h2 class="form-signin-heading">SIGN UP</h2>
                <label for="username" class="sr-only">Username</label>
                <input type="text" id="username" name="username" class="form-control" placeholder="Username" required>
                <label for="password" class="sr-only">Password</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="password" required>
                <label for="password2" class="sr-only">Confirm Password</label>
                <input type="password" id="password2" name="password2" class="form-control" placeholder="confirm" required>
                <label for="nome" class="sr-only">Nome</label>
                <input type="text" id="nome" name="nome" class="form-control" placeholder="Nome" required>
                <label for="email" class="sr-only">Email</label>
                <input type="text" id="email" name="email" class="form-control" placeholder="E-mail" required>
                <label for="birthday" class="sr-only">Data de Nascimento</label>
                <input type="date" id="birthday" name="birthday" class="form-control" placeholder="dd/mm/yyyy" required>
                <label for="photo" class="sr-only">Photo</label>
                <input type="file" id="file" name="file" class="form-control" required>
                <input type="hidden" name="command" value="user.insert"/>
                <button class="btn btn-lg btn-primary btn-block" type="submit">SIGN UP</button>
            </form>
        </div>
    </body>
</html>