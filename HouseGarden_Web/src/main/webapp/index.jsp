<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HouseGarden - Inicio</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

<!-- NAVBAR -->
<nav class="navbar">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">
        🌿 House<span>Garden</span>
    </a>
    <ul class="navbar-nav">
        <li><a href="${pageContext.request.contextPath}/" class="active">Inicio</a></li>
        <li><a href="${pageContext.request.contextPath}/productos">Productos</a></li>
        <li><a href="${pageContext.request.contextPath}/clientes">Clientes</a></li>
        <li><a href="${pageContext.request.contextPath}/proveedores">Proveedores</a></li>
        <li><a href="${pageContext.request.contextPath}/ordenes">Órdenes</a></li>
    </ul>
</nav>

<div class="container">

    <!-- Encabezado -->
    <div style="text-align:center; padding: 3rem 0 2rem;">
        <h1 style="font-family:'Playfair Display',serif; font-size:2.5rem; color:#2d5016;">
            🌿 Sistema HouseGarden
        </h1>
        <p style="color:#666; margin-top:0.5rem; font-size:1rem;">
            Sistema de Gestión de Productos para Vivero
        </p>
        <p style="color:#999; font-size:0.85rem; margin-top:0.25rem;">
            Aprendiz: Laura Cómbita y Diana Amaya — Ficha 3118327 — SENA
        </p>
    </div>

    <!-- Dashboard Cards -->
    <div class="dashboard-grid">
        <a class="dash-card" href="${pageContext.request.contextPath}/productos">
            <div class="icon">🌱</div>
            <h3>Productos</h3>
            <p>Gestionar catálogo de plantas y herramientas</p>
        </a>
        <a class="dash-card" href="${pageContext.request.contextPath}/clientes">
            <div class="icon">👤</div>
            <h3>Clientes</h3>
            <p>Administrar clientes registrados</p>
        </a>
        <a class="dash-card" href="${pageContext.request.contextPath}/proveedores">
            <div class="icon">🚚</div>
            <h3>Proveedores</h3>
            <p>Gestionar proveedores del vivero</p>
        </a>
        <a class="dash-card" href="${pageContext.request.contextPath}/ordenes">
            <div class="icon">📋</div>
            <h3>Órdenes</h3>
            <p>Registrar y consultar pedidos</p>
        </a>
    </div>

</div>

<footer>
    &copy; 2025 HouseGarden &mdash; SENA Análisis y Desarrollo de Software &mdash; Laura Cómbita
</footer>

</body>
</html>
