/**
 * ============================================================
 * COMPONENTE RAÍZ: App.js
 * ============================================================
 * Componente principal de la aplicación HouseGarden.
 * Actúa como punto de entrada de todos los módulos del sistema.
 *
 * Maneja el estado de navegación con useState para mostrar
 * la vista activa sin necesidad de un enrutador externo,
 * siguiendo el patrón visto en clase (sesión 9 - 15/04/26).
 *
 * Módulos del sistema:
 *   - inicio      : Dashboard principal con tarjetas de acceso
 *   - productos   : Gestión de plantas y herramientas del vivero
 *   - clientes    : Registro y administración de clientes
 *   - proveedores : Gestión de empresas proveedoras
 *   - ordenes     : Registro y seguimiento de pedidos
 *
 * Proyecto  : HouseGarden - Vivero
 * Evidencia : GA7-220501096-AA4-EV03
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * Ficha     : 3118327 | SENA 2026
 * ============================================================
 */
import React, { useState } from 'react';

// Importación de componentes de cada módulo
import Navbar        from './components/Navbar';
import Inicio        from './components/Inicio';
import ListaProductos   from './components/productos/ListaProductos';
import ListaClientes    from './components/clientes/ListaClientes';
import ListaProveedores from './components/proveedores/ListaProveedores';
import ListaOrdenes     from './components/ordenes/ListaOrdenes';

/**
 * Componente App - Raíz de la aplicación.
 * Controla qué módulo (vista) se muestra usando el estado 'vistaActiva'.
 *
 * @returns {JSX.Element} La estructura principal de la aplicación
 */
function App() {

  /**
   * Estado que controla qué módulo está activo.
   * Valores posibles: 'inicio' | 'productos' | 'clientes' | 'proveedores' | 'ordenes'
   * Se usa useState siguiendo lo visto en clase (sesión 9).
   */
  const [vistaActiva, setVistaActiva] = useState('inicio');

  /**
   * Función que cambia la vista activa.
   * Se pasa como prop al componente Navbar para manejar la navegación.
   *
   * @param {string} vista - Nombre de la vista a mostrar
   */
  const cambiarVista = (vista) => {
    setVistaActiva(vista);
  };

  /**
   * Función que renderiza el componente de la vista activa.
   * Actúa como enrutador simple basado en el estado vistaActiva.
   *
   * @returns {JSX.Element} El componente correspondiente a la vista activa
   */
  const renderizarVista = () => {
    switch (vistaActiva) {
      case 'productos':
        return <ListaProductos />;
      case 'clientes':
        return <ListaClientes />;
      case 'proveedores':
        return <ListaProveedores />;
      case 'ordenes':
        return <ListaOrdenes />;
      default:
        // Vista por defecto: dashboard de inicio
        return <Inicio cambiarVista={cambiarVista} />;
    }
  };

  return (
    <div>
      {/* Barra de navegación - recibe la función cambiarVista y la vista activa como props */}
      <Navbar vistaActiva={vistaActiva} cambiarVista={cambiarVista} />

      {/* Contenido principal - renderiza la vista activa */}
      <div className="container">
        {renderizarVista()}
      </div>

      {/* Pie de página */}
      <footer>
        &copy; 2026 HouseGarden &mdash; SENA &mdash; Laura Cómbita, Diana Amaya &amp; Andrés Fontalvo
      </footer>
    </div>
  );
}

export default App;
