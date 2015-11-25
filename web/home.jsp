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
            <c:forEach items="${carros}" var="carro">
                <div class="veiculo-container">
                    <image class="veiculo" src="img/veiculos/veiculo${carro.getId_carro()}.jpg"/>
                    <c:choose>
                        <c:when test="${carro.getQuantidade() > 0}">
                            <a class="btn btn-lg btn-block btn-success" href="FrontController?idcarro=${carro.getId_carro()}&command=carro.info">R$${carro.getPreco()},00</a>
                        </c:when>
                        <c:when test="${carro.getQuantidade() < 1}">
                            <a class="btn btn-lg btn-block btn-danger" href="FrontController?idcarro=${carro.getId_carro()}&command=carro.info">ESGOTADO</a>
                        </c:when>
                    </c:choose>

                </div>
            </c:forEach>
        </div>

    </body>
</html>