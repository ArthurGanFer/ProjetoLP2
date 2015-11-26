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
                <c:redirect url="index.jsp"></c:redirect>
            </c:when>
        </c:choose>

        <div class="container">
            <table class="table table-striped">
                <tr>
                    <th>#id</th>
                    <th>Username</th>
                    <th>Nome</th>
                    <th>Email</th>
                    <th>Ação</th>
                </tr>
                <c:forEach items="${usuarios}" var="usuario">
                    <tr>
                        <td>${usuario.getId_user()}</td>
                        <td>${usuario.getUsername()}</td>
                        <td>${usuario.getUsuario_info().getNome()}</td>
                        <td>${usuario.getUsuario_info().getEmail()}</td>
                        <td>
                            <div>
                                <a class="btn btn-xs btn-success" href="FrontController?username=${usuario.getUsername()}&command=admin.promover">Promover</a>
                                <a class="btn btn-xs btn-danger" href="FrontController?username=${usuario.getUsername()}&command=admin.banir">BAN</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
