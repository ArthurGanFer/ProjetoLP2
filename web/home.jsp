<%-- 
    Document   : home
    Created on : 30/09/2015, 09:47:23
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
    <c:if test="${user == null}">
        <c:redirect url="index.jsp"></c:redirect>
    </c:if>
        <img alt="imagem" class="avatar" src="http://localhost:8080/ProjetoBase_03H/img/${user.getUsername()}/${user.getPhoto()}"/>
        <h1>Welcome ${user.getUsername()}</h1>
             
        <c:choose>
            <c:when test="${user.getUser_type() == 2}">
                <%@include file="menu_admin.jspf" %>
            </c:when>
            <c:when test="${user.getUser_type() == 1}">
                <%@include file="menu_user.jspf" %>
            </c:when>
        </c:choose>
        
        <hr>
            
        
    </body>
</html>
