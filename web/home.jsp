<%-- 
    Document   : home
    Created on : 30/09/2015, 09:47:28
    Author     : 31338283
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projeto LP2</title>
    </head>
    <body>
        <!--
                <h1>$(nome)</h1>-->

        <h1><%
            out.print("Bem vindo " + request.getSession().getAttribute("username").toString());
            out.print("</br>Sua senha é " + request.getSession().getAttribute("password").toString());
            %></h1>
    </body>
</html>
