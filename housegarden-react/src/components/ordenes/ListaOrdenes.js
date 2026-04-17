/**
 * ============================================================
 * COMPONENTE: ListaOrdenes.js
 * ============================================================
 * Módulo de gestión de Órdenes del sistema HouseGarden.
 * Incluye lista desplegable de clientes para seleccionar al crear una orden.
 *
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * ============================================================
 */
import React, { useState } from 'react';
import FormularioOrden from './FormularioOrden';
import Alerta from '../Alerta';

function ListaOrdenes() {

  // Lista de clientes disponibles para el select del formulario
  const clientesDisponibles = [
    { id: 1, nombre: 'Juan Pérez' },
    { id: 2, nombre: 'Ana Torres' },
  ];

  const [ordenes, setOrdenes] = useState([
    { id: 1, clienteNombre: 'Juan Pérez', fecha: '2026-01-10', estado: 'Completado', total: 45000 },
    { id: 2, clienteNombre: 'Ana Torres', fecha: '2026-02-15', estado: 'Pendiente',  total: 25000 },
  ]);

  const [mostrarFormulario, setMostrarFormulario] = useState(false);
  const [ordenEditando, setOrdenEditando]         = useState(null);
  const [alerta, setAlerta]                       = useState({ mensaje: '', tipo: '' });

  const mostrarAlerta = (mensaje, tipo) => {
    setAlerta({ mensaje, tipo });
    setTimeout(() => setAlerta({ mensaje: '', tipo: '' }), 3000);
  };

  const abrirNuevo  = () => { setOrdenEditando(null); setMostrarFormulario(true); };
  const abrirEditar = (o) => { setOrdenEditando(o);   setMostrarFormulario(true); };

  const guardarOrden = (orden) => {
    if (orden.id) {
      setOrdenes(ordenes.map(o => o.id === orden.id ? orden : o));
      mostrarAlerta('Orden actualizada correctamente', 'success');
    } else {
      setOrdenes([...ordenes, { ...orden, id: Date.now() }]);
      mostrarAlerta('Orden guardada correctamente', 'success');
    }
    setMostrarFormulario(false);
  };

  const eliminarOrden = (id) => {
    if (window.confirm('¿Está seguro de eliminar esta orden?')) {
      setOrdenes(ordenes.filter(o => o.id !== id));
      mostrarAlerta('Orden eliminada correctamente', 'success');
    }
  };

  /**
   * Retorna la clase CSS del badge según el estado de la orden.
   * @param {string} estado - Estado de la orden
   * @returns {string} Clase CSS del badge
   */
  const getBadgeClase = (estado) => {
    const mapa = {
      'Pendiente':  'badge badge-pendiente',
      'Completado': 'badge badge-completado',
      'Cancelado':  'badge badge-cancelado',
    };
    return mapa[estado] || 'badge';
  };

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">📋 Órdenes</h1>
        <button className="btn btn-primary" onClick={abrirNuevo}>+ Nueva Orden</button>
      </div>

      <Alerta mensaje={alerta.mensaje} tipo={alerta.tipo} />

      {mostrarFormulario && (
        <div style={{ marginBottom: '1.5rem' }}>
          <FormularioOrden
            ordenEditando={ordenEditando}
            clientes={clientesDisponibles}
            onGuardar={guardarOrden}
            onCancelar={() => setMostrarFormulario(false)}
          />
        </div>
      )}

      <div className="table-wrapper">
        <table>
          <thead>
            <tr><th>#</th><th>Cliente</th><th>Fecha</th><th>Estado</th><th>Total</th><th>Acciones</th></tr>
          </thead>
          <tbody>
            {ordenes.length === 0 ? (
              <tr><td colSpan="6" className="empty-msg">No hay órdenes registradas.</td></tr>
            ) : (
              ordenes.map((o) => (
                <tr key={o.id}>
                  <td>{o.id}</td>
                  <td><strong>{o.clienteNombre}</strong></td>
                  <td>{o.fecha}</td>
                  <td><span className={getBadgeClase(o.estado)}>{o.estado}</span></td>
                  <td>${o.total.toLocaleString()}</td>
                  <td className="acciones">
                    <button className="btn btn-warning btn-sm" onClick={() => abrirEditar(o)}>Editar</button>
                    <button className="btn btn-danger btn-sm" onClick={() => eliminarOrden(o.id)}>Eliminar</button>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default ListaOrdenes;
