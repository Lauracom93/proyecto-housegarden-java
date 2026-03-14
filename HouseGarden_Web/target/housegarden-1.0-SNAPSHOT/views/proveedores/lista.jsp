<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Proveedores - HouseGarden</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<nav class="navbar">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">🌿 House<span>Garden</span></a>
    <ul class="navbar-nav">
        <li><a href="${pageContext.request.contextPath}/">Inicio</a></li>
        <li><a href="${pageContext.request.contextPath}/productos">Productos</a></li>
        <li><a href="${pageContext.request.contextPath}/clientes">Clientes</a></li>
        <li><a href="${pageContext.request.contextPath}/proveedores" class="active">Proveedores</a></li>
        <li><a href="${pageContext.request.contextPath}/ordenes">Órdenes</a></li>
    </ul>
</nav>
<div class="container">
    <div class="page-header">
        <h1 class="page-title">🚚 Proveedores <small>Proveedores del vivero</small></h1>
        <a href="${pageContext.request.contextPath}/proveedores?accion=nuevo" class="btn btn-primary">+ Nuevo Proveedor</a>
    </div>
    <c:if test="${not empty msg}">
        <c:choose>
            <c:when test="${msg == 'guardado'}"><div class="alert alert-success">✔ Proveedor guardado.</div></c:when>
            <c:when test="${msg == 'actualizado'}"><div class="alert alert-success">✔ Proveedor actualizado.</div></c:when>
            <c:when test="${msg == 'eliminado'}"><div class="alert alert-success">✔ Proveedor eliminado.</div></c:when>
        </c:choose>
    </c:if>
    <c:if test="${not empty error}"><div class="alert alert-danger">⚠ ${error}</div></c:if>
    <div class="table-wrapper">
        <table>
            <thead>
                <tr><th>#</th><th>Nombre</th><th>Contacto</th><th>Teléfono</th><th>Acciones</th></tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty proveedores}">
                        <tr><td colspan="5" style="text-align:center;color:#999;padding:2rem;">No hay proveedores registrados.</td></tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="p" items="${proveedores}">
                            <tr>
                                <td>${p.idProveedor}</td>
                                <td><strong>${p.nombre}</strong></td>
                                <td>${p.contacto}</td>
                                <td>${p.telefono}</td>
                                <td class="acciones">
                                    <a href="${pageContext.request.contextPath}/proveedores?accion=editar&id=${p.idProveedor}" class="btn btn-warning btn-sm">Editar</a>
                                    <a href="${pageContext.request.contextPath}/proveedores?accion=eliminar&id=${p.idProveedor}" class="btn btn-danger btn-sm" onclick="return confirm('¿Eliminar este proveedor?')">Eliminar</a>
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
