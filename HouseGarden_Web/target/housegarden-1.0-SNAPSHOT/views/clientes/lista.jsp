<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Clientes - HouseGarden</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

<nav class="navbar">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">🌿 House<span>Garden</span></a>
    <ul class="navbar-nav">
        <li><a href="${pageContext.request.contextPath}/">Inicio</a></li>
        <li><a href="${pageContext.request.contextPath}/productos">Productos</a></li>
        <li><a href="${pageContext.request.contextPath}/clientes" class="active">Clientes</a></li>
        <li><a href="${pageContext.request.contextPath}/proveedores">Proveedores</a></li>
        <li><a href="${pageContext.request.contextPath}/ordenes">Órdenes</a></li>
    </ul>
</nav>

<div class="container">

    <div class="page-header">
        <h1 class="page-title">👤 Clientes <small>Clientes registrados en el sistema</small></h1>
        <a href="${pageContext.request.contextPath}/clientes?accion=nuevo" class="btn btn-primary">+ Nuevo Cliente</a>
    </div>

    <c:if test="${not empty msg}">
        <c:choose>
            <c:when test="${msg == 'guardado'}"><div class="alert alert-success">✔ Cliente guardado correctamente.</div></c:when>
            <c:when test="${msg == 'actualizado'}"><div class="alert alert-success">✔ Cliente actualizado correctamente.</div></c:when>
            <c:when test="${msg == 'eliminado'}"><div class="alert alert-success">✔ Cliente eliminado correctamente.</div></c:when>
        </c:choose>
    </c:if>
    <c:if test="${not empty error}"><div class="alert alert-danger">⚠ ${error}</div></c:if>

    <div class="table-wrapper">
        <table>
            <thead>
                <tr>
                    <th>#</th><th>Nombre</th><th>Correo</th><th>Teléfono</th><th>Fecha Registro</th><th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty clientes}">
                        <tr><td colspan="6" style="text-align:center;color:#999;padding:2rem;">No hay clientes registrados.</td></tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="c" items="${clientes}">
                            <tr>
                                <td>${c.idCliente}</td>
                                <td><strong>${c.nombre}</strong></td>
                                <td>${c.correo}</td>
                                <td>${c.telefono}</td>
                                <td>${c.fechaRegistro}</td>
                                <td class="acciones">
                                    <a href="${pageContext.request.contextPath}/clientes?accion=editar&id=${c.idCliente}" class="btn btn-warning btn-sm">Editar</a>
                                    <a href="${pageContext.request.contextPath}/clientes?accion=eliminar&id=${c.idCliente}" class="btn btn-danger btn-sm" onclick="return confirm('¿Eliminar este cliente?')">Eliminar</a>
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
