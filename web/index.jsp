<%-- 
    Document   : index
    Created on : 30/09/2015, 07:49:01
    Author     : 31338283
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <form action="FrontController" method="POST">
            <fieldset>
                <legend>LOGIN</legend>
                <p> Username: *<input type="text" name="username" placeholder="username" required/></p>
                <p> Password: *<input type="password" name="password" placeholder="password" required/></p>
                <p><input type="submit" value="LOGIN"></p>
                <input type="hidden" name="command" value="user.login"/>
                
                <a href="register.jsp">SIGN UP</a>
            </fieldset>
        </form>
    </body>
</html>
