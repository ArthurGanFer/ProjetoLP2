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
            <image class="veiculo" src="img/veiculos/veiculo${carroinfo.getId_carro()}.jpg"/>
            <p>Você solicitou a compra de um ${carro.getMarca()} ${carro.getModelo()} no valor de R$${carro.getPreco()}.</p>
            <p>Acompanhe a aprovação da compra da compra pela aba de historicos</p>
            <a class="btn btn-default" href="home.jsp">Voltar</a>
        </div>
    </body>
</html>
