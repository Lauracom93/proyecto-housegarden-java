<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${empty cliente ? 'Nuevo Cliente' : 'Editar Cliente'} - HouseGarden</title>
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
        <h1 class="page-title">
            <c:choose>
                <c:when test="${empty cliente}">👤 Nuevo Cliente</c:when>
                <c:otherwise>✏️ Editar Cliente</c:otherwise>
            </c:choose>
        </h1>
        <a href="${pageContext.request.contextPath}/clientes" class="btn btn-secondary">← Volver</a>
    </div>

    <c:if test="${not empty error}"><div class="alert alert-danger">⚠ ${error}</div></c:if>

    <div class="card">
        <form action="${pageContext.request.contextPath}/clientes" method="post">

            <c:if test="${not empty cliente}">
                <input type="hidden" name="idCliente" value="${cliente.idCliente}">
            </c:if>

            <div class="form-group">
                <label for="nombre">Nombre completo</label>
                <input type="text" id="nombre" name="nombre" class="form-control"
                       placeholder="Ej: Juan Pérez" value="${cliente.nombre}" required>
            </div>

            <div class="form-group">
                <label for="correo">Correo electrónico</label>
                <input type="email" id="correo" name="correo" class="form-control"
                       placeholder="Ej: juan@email.com" value="${cliente.correo}" required>
            </div>

            <div class="form-group">
                <label for="telefono">Teléfono</label>
                <input type="text" id="telefono" name="telefono" class="form-control"
                       placeholder="Ej: 3001234567" value="${cliente.telefono}">
            </div>

            <div class="form-group">
                <label for="fechaRegistro">Fecha de Registro</label>
                <input type="date" id="fechaRegistro" name="fechaRegistro" class="form-control"
                       value="${cliente.fechaRegistro}" required>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">
                    <c:choose>
                        <c:when test="${empty cliente}">💾 Guardar Cliente</c:when>
                        <c:otherwise>✔ Actualizar Cliente</c:otherwise>
                    </c:choose>
                </button>
                <a href="${pageContext.request.contextPath}/clientes" class="btn btn-secondary">Cancelar</a>
            </div>
        </form>
    </div>
</div>

<footer>&copy; 2025 HouseGarden &mdash; SENA &mdash; Laura Cómbita</footer>
</body>
</html>
