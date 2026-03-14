<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Productos - HouseGarden</title>
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
            🌱 Productos
            <small>Catálogo de plantas y herramientas</small>
        </h1>
        <a href="${pageContext.request.contextPath}/productos?accion=nuevo" class="btn btn-primary">
            + Nuevo Producto
        </a>
    </div>

    <%-- Mensajes JSP con JSTL --%>
    <c:if test="${not empty msg}">
        <c:choose>
            <c:when test="${msg == 'guardado'}">
                <div class="alert alert-success">✔ Producto guardado correctamente.</div>
            </c:when>
            <c:when test="${msg == 'actualizado'}">
                <div class="alert alert-success">✔ Producto actualizado correctamente.</div>
            </c:when>
            <c:when test="${msg == 'eliminado'}">
                <div class="alert alert-success">✔ Producto eliminado correctamente.</div>
            </c:when>
        </c:choose>
    </c:if>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">⚠ ${error}</div>
    </c:if>

    <div class="table-wrapper">
        <table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Precio</th>
                    <th>Stock</th>
                    <th>Categoría</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty productos}">
                        <tr>
                            <td colspan="7" style="text-align:center; color:#999; padding:2rem;">
                                No hay productos registrados.
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="p" items="${productos}">
                            <tr>
                                <td>${p.idProducto}</td>
                                <td><strong>${p.nombre}</strong></td>
                                <td>${p.descripcion}</td>
                                <td>
                                    <fmt:formatNumber value="${p.precio}" type="currency"
                                                      currencySymbol="$" maxFractionDigits="0"/>
                                </td>
                                <td>${p.stock}</td>
                                <td>${p.idCategoria}</td>
                                <td class="acciones">
                                    <a href="${pageContext.request.contextPath}/productos?accion=editar&id=${p.idProducto}"
                                       class="btn btn-warning btn-sm">Editar</a>
                                    <a href="${pageContext.request.contextPath}/productos?accion=eliminar&id=${p.idProducto}"
                                       class="btn btn-danger btn-sm"
                                       onclick="return confirm('¿Eliminar este producto?')">Eliminar</a>
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
