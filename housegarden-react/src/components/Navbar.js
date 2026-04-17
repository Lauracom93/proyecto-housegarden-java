/**
 * ============================================================
 * COMPONENTE: Navbar.js
 * ============================================================
 * Barra de navegación principal del sistema HouseGarden.
 * Componente reutilizable que se muestra en todas las vistas.
 *
 * Props recibidas del componente padre App.js:
 *   @prop {string}   vistaActiva  - Vista que está actualmente activa
 *   @prop {Function} cambiarVista - Función para cambiar la vista activa
 *
 * Patrón usado: Comunicación padre -> hijo mediante props
 * (explicado en clase sesión 9 - 15/04/26)
 *
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * ============================================================
 */
import React from 'react';

/**
 * Componente funcional Navbar.
 * Muestra el logo y los botones de navegación del sistema.
 *
 * @param {Object} props - Propiedades del componente
 * @param {string} props.vistaActiva - Vista actualmente visible
 * @param {Function} props.cambiarVista - Función para cambiar de vista
 * @returns {JSX.Element} Barra de navegación con menú de módulos
 */
function Navbar({ vistaActiva, cambiarVista }) {

  // Lista de módulos del sistema para generar el menú dinámicamente
  const modulos = [
    { id: 'inicio',      label: 'Inicio',      emoji: '🏠' },
    { id: 'productos',   label: 'Productos',   emoji: '🌱' },
    { id: 'clientes',    label: 'Clientes',    emoji: '👤' },
    { id: 'proveedores', label: 'Proveedores', emoji: '🚚' },
    { id: 'ordenes',     label: 'Órdenes',     emoji: '📋' },
  ];

  return (
    <nav className="navbar">
      {/* Logo / Marca del sistema */}
      <span
        className="navbar-brand"
        onClick={() => cambiarVista('inicio')}
        title="Ir al inicio"
      >
        🌿 House<span>Garden</span>
      </span>

      {/* Menú de navegación - generado dinámicamente desde el array modulos */}
      <ul className="navbar-menu">
        {modulos.map((modulo) => (
          <li key={modulo.id}>
            <button
              className={vistaActiva === modulo.id ? 'activo' : ''}
              onClick={() => cambiarVista(modulo.id)}
            >
              {modulo.emoji} {modulo.label}
            </button>
          </li>
        ))}
      </ul>
    </nav>
  );
}

export default Navbar;
