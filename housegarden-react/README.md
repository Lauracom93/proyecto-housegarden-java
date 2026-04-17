# 🌿 HouseGarden - Frontend React JS

Sistema de Gestión de Productos para Vivero  
Evidencia: GA7-220501096-AA4-EV03

## Aprendices
- Laura Angélica Cómbita
- Diana Carina Amaya
- Andrés Fontalvo

**Ficha:** 3118327 | **SENA** | Análisis y Desarrollo de Software | 2026

## Tecnologías
- React JS 18
- JavaScript ES6+
- CSS3
- Node.js

## Cómo ejecutar en Visual Studio Code

### Requisitos previos
1. Instalar [Node.js](https://nodejs.org) (versión 16 o superior)
2. Instalar [Visual Studio Code](https://code.visualstudio.com)

### Pasos para ejecutar

1. Abrir la carpeta `housegarden-react` en Visual Studio Code
2. Abrir la terminal integrada: **Terminal → New Terminal** (o Ctrl + `)
3. Instalar las dependencias:
```
npm install
```
4. Iniciar la aplicación:
```
npm start
```
5. Se abre automáticamente el navegador en:
```
http://localhost:3000
```

## Estructura del proyecto
```
housegarden-react/
├── public/
│   └── index.html          # Punto de entrada HTML
├── src/
│   ├── components/
│   │   ├── Navbar.js           # Barra de navegación
│   │   ├── Inicio.js           # Dashboard principal
│   │   ├── Alerta.js           # Mensajes de éxito/error
│   │   ├── productos/
│   │   │   ├── ListaProductos.js    # Lista + lógica del módulo
│   │   │   └── FormularioProducto.js # Formulario crear/editar
│   │   ├── clientes/
│   │   │   ├── ListaClientes.js
│   │   │   └── FormularioCliente.js
│   │   ├── proveedores/
│   │   │   ├── ListaProveedores.js
│   │   │   └── FormularioProveedor.js
│   │   └── ordenes/
│   │       ├── ListaOrdenes.js
│   │       └── FormularioOrden.js
│   ├── App.js              # Componente raíz - maneja la navegación
│   ├── App.css             # Estilos globales
│   └── index.js            # Punto de entrada de React
├── package.json
└── README.md
```

## Repositorio GitHub
https://github.com/Lauracom93/proyecto-housegarden-java
