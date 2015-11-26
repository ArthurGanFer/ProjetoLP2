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
                <div class="container">
                    <image class="veiculo" src="img/veiculos/veiculo${carroinfo.getId_carro()}.jpg"/>
                    <form action="FrontController" method="POST">
                        <ul>
                            <li>
                                <p>Marca - ${carroinfo.getMarca()}</p>
                            </li>
                            <li>
                                <p>Modelo - ${carroinfo.getModelo()}</p>
                            </li>
                            <li>
                                <p>Ano - ${carroinfo.getAno()}</p>
                            </li>
                            <li>
                                <p>Quantidade disponivel - <input type="number" name="quantidade" value="${carroinfo.getQuantidade()}" required/></p>
                            </li>
                            <li>
                                <p>Preço - R$<input type="number" name="preco" value="${carroinfo.getPreco()}" required/>,00</p>
                            </li>
                        </ul>
                        <input type="hidden" name="idcarro" value="${carroinfo.getId_carro()}"/>
                        <input type="hidden" name="command" value="admin.update"/>
                        <button class="btn btn-info" type="submit">Atualizar</button>
                    </form>
                    <a class="btn btn-default" href="home.jsp">Voltar</a>
                </div>
            </c:when>
            <c:when test="${user.getUser_type() == 1}">
                <%@include file="menu_user.jspf" %>
                <div class="container">
                    <image class="veiculo" src="img/veiculos/veiculo${carroinfo.getId_carro()}.jpg"/>
                    <ul>
                        <li>
                            <p>Marca - ${carroinfo.getMarca()}</p>
                        </li>
                        <li>
                            <p>Modelo - ${carroinfo.getModelo()}</p>
                        </li>
                        <li>
                            <p>Ano - ${carroinfo.getAno()}</p>
                        </li>
                        <li>
                            <p>Quantidade disponivel - ${carroinfo.getQuantidade() > 0 ?carroinfo.getQuantidade():'ESGOTADO'}</p>
                        </li>
                        <li>
                            <p>Preço - R$${carroinfo.getPreco()},00</p>
                        </li>
                    </ul>
                    <c:choose>
                        <c:when test="${carroinfo.getQuantidade() > 0}">
                            <a class="btn btn-success" href="FrontController?idcarro=${carroinfo.getId_carro()}&idusuario=${user.getId_user()}&command=compra.confirmar">Comprar</a>
                        </c:when>
                        <c:when test="${carroinfo.getQuantidade() < 1}">
                            <a class="btn btn-default disabled">Comprar</a>
                        </c:when>
                    </c:choose>
                    <a class="btn btn-default" href="home.jsp">Voltar</a>
                </div>
            </c:when>
        </c:choose>
    </body>
</html>
