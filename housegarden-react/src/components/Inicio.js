/**
 * ============================================================
 * COMPONENTE: Inicio.js
 * ============================================================
 * Dashboard principal del sistema HouseGarden.
 * Muestra tarjetas de acceso rápido a cada módulo del sistema.
 *
 * Props recibidas:
 *   @prop {Function} cambiarVista - Función para navegar a otro módulo
 *
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * ============================================================
 */
import React from 'react';

/**
 * Componente funcional Inicio.
 * Muestra el dashboard con tarjetas de acceso a cada módulo.
 *
 * @param {Object} props - Propiedades del componente
 * @param {Function} props.cambiarVista - Función para cambiar de módulo
 * @returns {JSX.Element} Dashboard con tarjetas de acceso
 */
function Inicio({ cambiarVista }) {

  // Configuración de las tarjetas del dashboard
  const tarjetas = [
    {
      id: 'productos',
      emoji: '🌱',
      titulo: 'Productos',
      descripcion: 'Gestionar catálogo de plantas y herramientas del vivero'
    },
    {
      id: 'clientes',
      emoji: '👤',
      titulo: 'Clientes',
      descripcion: 'Administrar clientes registrados en el sistema'
    },
    {
      id: 'proveedores',
      emoji: '🚚',
      titulo: 'Proveedores',
      descripcion: 'Gestionar empresas proveedoras del vivero'
    },
    {
      id: 'ordenes',
      emoji: '📋',
      titulo: 'Órdenes',
      descripcion: 'Registrar y consultar pedidos de clientes'
    },
  ];

  return (
    <div>
      {/* Encabezado del dashboard */}
      <div style={{ textAlign: 'center', padding: '3rem 0 2rem' }}>
        <h1 style={{ fontSize: '2.4rem', color: '#2d5016', fontWeight: '700' }}>
          🌿 Sistema HouseGarden
        </h1>
        <p style={{ color: '#666', marginTop: '0.5rem', fontSize: '1rem' }}>
          Sistema de Gestión de Productos para Vivero
        </p>
        <p style={{ color: '#999', fontSize: '0.85rem', marginTop: '0.25rem' }}>
          Laura Cómbita, Diana Amaya & Andrés Fontalvo — Ficha 3118327 — SENA 2026
        </p>
        <p style={{ color: '#4a7c20', fontSize: '0.85rem', marginTop: '0.25rem', fontWeight: '600' }}>
          Frontend: React JS 18 | Backend: Spring Boot 3.2
        </p>
      </div>

      {/* Grid de tarjetas del dashboard */}
      <div className="dashboard-grid">
        {/* Cada tarjeta usa onClick para cambiar la vista usando la prop cambiarVista */}
        {tarjetas.map((tarjeta) => (
          <div
            key={tarjeta.id}
            className="dash-card"
            onClick={() => cambiarVista(tarjeta.id)}
            title={`Ir a ${tarjeta.titulo}`}
          >
            <div className="icon">{tarjeta.emoji}</div>
            <h3>{tarjeta.titulo}</h3>
            <p>{tarjeta.descripcion}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Inicio;
