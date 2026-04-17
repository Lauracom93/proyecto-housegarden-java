/**
 * ============================================================
 * COMPONENTE: ListaClientes.js
 * ============================================================
 * Módulo de gestión de Clientes del sistema HouseGarden.
 * Mismo patrón que ListaProductos (visto en clase sesión 9):
 *   - useState para lista y formulario
 *   - Props para comunicación con FormularioCliente
 *
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * ============================================================
 */
import React, { useState } from 'react';
import FormularioCliente from './FormularioCliente';
import Alerta from '../Alerta';

function ListaClientes() {

  // Estado: lista de clientes con datos de ejemplo
  const [clientes, setClientes] = useState([
    { id: 1, nombre: 'Juan Pérez',  correo: 'juan@email.com', telefono: '3001234567', fechaRegistro: '2025-01-10' },
    { id: 2, nombre: 'Ana Torres',  correo: 'ana@email.com',  telefono: '3204567890', fechaRegistro: '2025-02-15' },
  ]);

  const [mostrarFormulario, setMostrarFormulario] = useState(false);
  const [clienteEditando, setClienteEditando]     = useState(null);
  const [alerta, setAlerta]                       = useState({ mensaje: '', tipo: '' });

  const mostrarAlerta = (mensaje, tipo) => {
    setAlerta({ mensaje, tipo });
    setTimeout(() => setAlerta({ mensaje: '', tipo: '' }), 3000);
  };

  const abrirNuevo   = () => { setClienteEditando(null);    setMostrarFormulario(true); };
  const abrirEditar  = (c) => { setClienteEditando(c);      setMostrarFormulario(true); };

  /**
   * Guarda o actualiza un cliente.
   * Recibida como prop onGuardar en FormularioCliente.
   */
  const guardarCliente = (cliente) => {
    if (cliente.id) {
      setClientes(clientes.map(c => c.id === cliente.id ? cliente : c));
      mostrarAlerta('Cliente actualizado correctamente', 'success');
    } else {
      setClientes([...clientes, { ...cliente, id: Date.now() }]);
      mostrarAlerta('Cliente guardado correctamente', 'success');
    }
    setMostrarFormulario(false);
  };

  const eliminarCliente = (id) => {
    if (window.confirm('¿Está seguro de eliminar este cliente?')) {
      setClientes(clientes.filter(c => c.id !== id));
      mostrarAlerta('Cliente eliminado correctamente', 'success');
    }
  };

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">👤 Clientes</h1>
        <button className="btn btn-primary" onClick={abrirNuevo}>+ Nuevo Cliente</button>
      </div>

      <Alerta mensaje={alerta.mensaje} tipo={alerta.tipo} />

      {mostrarFormulario && (
        <div style={{ marginBottom: '1.5rem' }}>
          <FormularioCliente
            clienteEditando={clienteEditando}
            onGuardar={guardarCliente}
            onCancelar={() => setMostrarFormulario(false)}
          />
        </div>
      )}

      <div className="table-wrapper">
        <table>
          <thead>
            <tr><th>#</th><th>Nombre</th><th>Correo</th><th>Teléfono</th><th>Fecha Registro</th><th>Acciones</th></tr>
          </thead>
          <tbody>
            {clientes.length === 0 ? (
              <tr><td colSpan="6" className="empty-msg">No hay clientes registrados.</td></tr>
            ) : (
              clientes.map((c) => (
                <tr key={c.id}>
                  <td>{c.id}</td>
                  <td><strong>{c.nombre}</strong></td>
                  <td>{c.correo}</td>
                  <td>{c.telefono}</td>
                  <td>{c.fechaRegistro}</td>
                  <td className="acciones">
                    <button className="btn btn-warning btn-sm" onClick={() => abrirEditar(c)}>Editar</button>
                    <button className="btn btn-danger btn-sm" onClick={() => eliminarCliente(c.id)}>Eliminar</button>
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

export default ListaClientes;
