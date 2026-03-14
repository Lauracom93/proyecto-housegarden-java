<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${empty producto ? 'Nuevo Producto' : 'Editar Producto'} - HouseGarden</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

<nav class="navbar">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">🌿 House<span>Garden</span></a>
    <ul class="navbar-nav">
        <li><a href="${pageContext.request.contextPath}/">Inicio</a></li>
        <li><a href="${pageContext.request.contextPath}/productos" class="active">Productos</a></li>
        <li><a href="${pageContext.request.contextPath}/clientes">Clientes</a></li>
        <li><a href="${pageContext.request.contextPath}/proveedores">Proveedores</a></li>
        <li><a href="${pageContext.request.contextPath}/ordenes">Órdenes</a></li>
    </ul>
</nav>

<div class="container">

    <div class="page-header">
        <h1 class="page-title">
            <c:choose>
                <c:when test="${empty producto}">🌱 Nuevo Producto</c:when>
                <c:otherwise>✏️ Editar Producto</c:otherwise>
            </c:choose>
        </h1>
        <a href="${pageContext.request.contextPath}/productos" class="btn btn-secondary">← Volver</a>
    </div>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">⚠ ${error}</div>
    </c:if>

    <div class="card">
        <%-- Formulario HTML que envia datos via POST al servlet --%>
        <form action="${pageContext.request.contextPath}/productos" method="post">

            <%-- Campo oculto con el ID para saber si es edición --%>
            <c:if test="${not empty producto}">
                <input type="hidden" name="idProducto" value="${producto.idProducto}">
            </c:if>

            <div class="form-group">
                <label for="nombre">Nombre del Producto</label>
                <input type="text" id="nombre" name="nombre" class="form-control"
                       placeholder="Ej: Rosa Roja, Pala de jardín..."
                       value="${producto.nombre}" required>
            </div>

            <div class="form-group">
                <label for="descripcion">Descripción</label>
                <textarea id="descripcion" name="descripcion" class="form-control"
                          rows="3" placeholder="Descripción del producto...">${producto.descripcion}</textarea>
            </div>

            <div class="form-group">
                <label for="precio">Precio (COP)</label>
                <input type="number" id="precio" name="precio" class="form-control"
                       placeholder="Ej: 15000" step="100" min="0"
                       value="${producto.precio}" required>
            </div>

            <div class="form-group">
                <label for="stock">Stock disponible</label>
                <input type="number" id="stock" name="stock" class="form-control"
                       placeholder="Ej: 20" min="0"
                       value="${producto.stock}" required>
            </div>

            <div class="form-group">
                <label for="idCategoria">ID Categoría</label>
                <input type="number" id="idCategoria" name="idCategoria" class="form-control"
                       placeholder="Ej: 1 (Plantas), 2 (Herramientas)"
                       value="${producto.idCategoria}">
            </div>

            <div class="form-group">
                <label for="idProveedor">ID Proveedor</label>
                <input type="number" id="idProveedor" name="idProveedor" class="form-control"
                       placeholder="Ej: 1"
                       value="${producto.idProveedor}">
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">
                    <c:choose>
                        <c:when test="${empty producto}">💾 Guardar Producto</c:when>
                        <c:otherwise>✔ Actualizar Producto</c:otherwise>
                    </c:choose>
                </button>
                <a href="${pageContext.request.contextPath}/productos" class="btn btn-secondary">Cancelar</a>
            </div>

        </form>
    </div>

</div>

<footer>&copy; 2025 HouseGarden &mdash; SENA &mdash; Laura Cómbita</footer>
</body>
</html>
