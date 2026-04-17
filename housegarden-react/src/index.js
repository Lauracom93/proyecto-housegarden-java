/**
 * Archivo principal de entrada de la aplicación React.
 * ReactDOM.render monta el componente raíz <App /> dentro
 * del div con id="root" del archivo public/index.html.
 *
 * Proyecto  : HouseGarden - Sistema de Gestión de Productos para Vivero
 * Framework : React JS 18
 * Evidencia : GA7-220501096-AA4-EV03
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * Ficha     : 3118327 | SENA 2026
 */
import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import './App.css';

// Crea el punto de montaje de React en el div id="root"
const root = ReactDOM.createRoot(document.getElementById('root'));

// Renderiza el componente principal App
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
