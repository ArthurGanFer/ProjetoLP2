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
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
    </head>
    <body>
        <c:if test="${user == null}">
            <c:redirect url="index.jsp"></c:redirect>
        </c:if>
        <c:choose>
            <c:when test="${user.getUser_type() == 0}">
                <%@include file="menu_admin.jspf" %>
            </c:when>
            <c:when test="${user.getUser_type() == 1}">
                <%@include file="menu_user.jspf" %>
            </c:when>
        </c:choose>

        <div class="container">
            <div class="veiculo-container">
                <a href="#"><image class="veiculo" src="img/veiculos/veiculo1.jpg"/></a>
            </div>

            <div class="veiculo-container">
                <a href="#"><image class="veiculo" src="img/veiculos/veiculo2.jpg"/></a>
            </div>

            <div class="veiculo-container">
                <a href="#"><image class="veiculo" src="img/veiculos/veiculo3.jpg"/></a>
            </div>

            <div class="veiculo-container">
                <a href="#"><image class="veiculo" src="img/veiculos/veiculo4.png"/></a>
            </div>

            <div class="veiculo-container">
                <a href="#"><image class="veiculo" src="img/veiculos/veiculo5.jpg"/></a>
            </div>

            <div class="veiculo-container">
                <a href="#"><image class="veiculo" src="img/veiculos/veiculo6.jpg"/></a>
            </div>

        </div>
    </body>
</html>
