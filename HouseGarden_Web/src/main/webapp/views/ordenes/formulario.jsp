<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${empty orden ? 'Nueva Orden' : 'Editar Orden'} - HouseGarden</title>
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
        <h1 class="page-title">
            <c:choose>
                <c:when test="${empty orden}">📋 Nueva Orden</c:when>
                <c:otherwise>✏️ Editar Orden</c:otherwise>
            </c:choose>
        </h1>
        <a href="${pageContext.request.contextPath}/ordenes" class="btn btn-secondary">← Volver</a>
    </div>
    <c:if test="${not empty error}"><div class="alert alert-danger">⚠ ${error}</div></c:if>
    <div class="card">
        <form action="${pageContext.request.contextPath}/ordenes" method="post">
            <c:if test="${not empty orden}">
                <input type="hidden" name="idOrden" value="${orden.idOrden}">
            </c:if>
           <div class="form-group">
    <label for="idCliente">Cliente</label>
    <select id="idCliente" name="idCliente" class="form-control" required>
        <option value="">-- Seleccione un cliente --</option>
        <c:forEach var="cli" items="${clientes}">
            <option value="${cli.idCliente}"
                <c:if test="${cli.idCliente == orden.idCliente}">selected</c:if>>
                ${cli.idCliente} - ${cli.nombre} (${cli.telefono})
            </option>
        </c:forEach>
    </select>
</div>
            <div class="form-group">
                <label for="fecha">Fecha de la Orden</label>
                <input type="date" id="fecha" name="fecha" class="form-control"
                       value="${orden.fecha}" required>
            </div>
            <div class="form-group">
                <label for="estado">Estado</label>
                <select id="estado" name="estado" class="form-control">
                    <option value="Pendiente"   ${orden.estado == 'Pendiente'   ? 'selected' : ''}>Pendiente</option>
                    <option value="Completado"  ${orden.estado == 'Completado'  ? 'selected' : ''}>Completado</option>
                    <option value="Cancelado"   ${orden.estado == 'Cancelado'   ? 'selected' : ''}>Cancelado</option>
                </select>
            </div>
            <div class="form-group">
                <label for="total">Total (COP)</label>
                <input type="number" id="total" name="total" class="form-control"
                       placeholder="Ej: 45000" step="100" min="0"
                       value="${orden.total}" required>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">
                    <c:choose>
                        <c:when test="${empty orden}">💾 Guardar Orden</c:when>
                        <c:otherwise>✔ Actualizar Orden</c:otherwise>
                    </c:choose>
                </button>
                <a href="${pageContext.request.contextPath}/ordenes" class="btn btn-secondary">Cancelar</a>
            </div>
        </form>
    </div>
</div>
<footer>&copy; 2025 HouseGarden &mdash; SENA &mdash; Laura Cómbita</footer>
</body>
</html>
