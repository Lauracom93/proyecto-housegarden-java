<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${empty proveedor ? 'Nuevo Proveedor' : 'Editar Proveedor'} - HouseGarden</title>
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
        <h1 class="page-title">
            <c:choose>
                <c:when test="${empty proveedor}">🚚 Nuevo Proveedor</c:when>
                <c:otherwise>✏️ Editar Proveedor</c:otherwise>
            </c:choose>
        </h1>
        <a href="${pageContext.request.contextPath}/proveedores" class="btn btn-secondary">← Volver</a>
    </div>
    <c:if test="${not empty error}"><div class="alert alert-danger">⚠ ${error}</div></c:if>
    <div class="card">
        <form action="${pageContext.request.contextPath}/proveedores" method="post">
            <c:if test="${not empty proveedor}">
                <input type="hidden" name="idProveedor" value="${proveedor.idProveedor}">
            </c:if>
            <div class="form-group">
                <label for="nombre">Nombre empresa / proveedor</label>
                <input type="text" id="nombre" name="nombre" class="form-control"
                       placeholder="Ej: Vivero Central" value="${proveedor.nombre}" required>
            </div>
            <div class="form-group">
                <label for="contacto">Persona de contacto</label>
                <input type="text" id="contacto" name="contacto" class="form-control"
                       placeholder="Ej: Carlos López" value="${proveedor.contacto}">
            </div>
            <div class="form-group">
                <label for="telefono">Teléfono</label>
                <input type="text" id="telefono" name="telefono" class="form-control"
                       placeholder="Ej: 3004567890" value="${proveedor.telefono}">
            </div>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">
                    <c:choose>
                        <c:when test="${empty proveedor}">💾 Guardar Proveedor</c:when>
                        <c:otherwise>✔ Actualizar Proveedor</c:otherwise>
                    </c:choose>
                </button>
                <a href="${pageContext.request.contextPath}/proveedores" class="btn btn-secondary">Cancelar</a>
            </div>
        </form>
    </div>
</div>
<footer>&copy; 2025 HouseGarden &mdash; SENA &mdash; Laura Cómbita</footer>
</body>
</html>
