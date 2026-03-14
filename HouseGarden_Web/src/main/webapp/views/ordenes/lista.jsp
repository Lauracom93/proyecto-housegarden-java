<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Órdenes - HouseGarden</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<nav class="navbar">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">🌿 House<span>Garden</span></a>
    <ul class="navbar-nav">
        <li><a href="${pageContext.request.contextPath}/">Inicio</a></li>
        <li><a href="${pageContext.request.contextPath}/productos">Productos</a></li>
        <li><a href="${pageContext.request.contextPath}/clientes">Clientes</a></li>
        <li><a href="${pageContext.request.contextPath}/proveedores">Proveedores</a></li>
        <li><a href="${pageContext.request.contextPath}/ordenes" class="active">Órdenes</a></li>
    </ul>
</nav>
<div class="container">
    <div class="page-header">
        <h1 class="page-title">📋 Órdenes <small>Registro de pedidos</small></h1>
        <a href="${pageContext.request.contextPath}/ordenes?accion=nuevo" class="btn btn-primary">+ Nueva Orden</a>
    </div>
    <c:if test="${not empty msg}">
        <c:choose>
            <c:when test="${msg == 'guardado'}"><div class="alert alert-success">✔ Orden guardada correctamente.</div></c:when>
            <c:when test="${msg == 'actualizado'}"><div class="alert alert-success">✔ Orden actualizada correctamente.</div></c:when>
            <c:when test="${msg == 'eliminado'}"><div class="alert alert-success">✔ Orden eliminada correctamente.</div></c:when>
        </c:choose>
    </c:if>
    <c:if test="${not empty error}"><div class="alert alert-danger">⚠ ${error}</div></c:if>
    <div class="table-wrapper">
        <table>
            <thead>
                <tr><th>#</th><th>Cliente ID</th><th>Fecha</th><th>Estado</th><th>Total</th><th>Acciones</th></tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty ordenes}">
                        <tr><td colspan="6" style="text-align:center;color:#999;padding:2rem;">No hay órdenes registradas.</td></tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="o" items="${ordenes}">
                            <tr>
                                <td>${o.idOrden}</td>
                                <td>${o.idCliente}</td>
                                <td>${o.fecha}</td>
                                <td>
                                    <span class="badge badge-${o.estado.toLowerCase()}">${o.estado}</span>
                                </td>
                                <td>$${o.total}</td>
                                <td class="acciones">
                                    <a href="${pageContext.request.contextPath}/ordenes?accion=editar&id=${o.idOrden}" class="btn btn-warning btn-sm">Editar</a>
                                    <a href="${pageContext.request.contextPath}/ordenes?accion=eliminar&id=${o.idOrden}" class="btn btn-danger btn-sm" onclick="return confirm('¿Eliminar esta orden?')">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</div>
<footer>&copy; 2025 HouseGarden &mdash; SENA &mdash; Laura Cómbita</footer>
</body>
</html>
