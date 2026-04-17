/**
 * ============================================================
 * COMPONENTE: ListaProveedores.js
 * ============================================================
 * Módulo de gestión de Proveedores del sistema HouseGarden.
 *
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * ============================================================
 */
import React, { useState } from 'react';
import FormularioProveedor from './FormularioProveedor';
import Alerta from '../Alerta';

function ListaProveedores() {

  const [proveedores, setProveedores] = useState([
    { id: 1, nombre: 'Vivero Central', contacto: 'Carlos López',  telefono: '3004567890' },
    { id: 2, nombre: 'Jardines SAS',   contacto: 'María Gómez',   telefono: '3109876543' },
  ]);

  const [mostrarFormulario, setMostrarFormulario] = useState(false);
  const [proveedorEditando, setProveedorEditando] = useState(null);
  const [alerta, setAlerta] = useState({ mensaje: '', tipo: '' });

  const mostrarAlerta = (mensaje, tipo) => {
    setAlerta({ mensaje, tipo });
    setTimeout(() => setAlerta({ mensaje: '', tipo: '' }), 3000);
  };

  const abrirNuevo  = () => { setProveedorEditando(null); setMostrarFormulario(true); };
  const abrirEditar = (p) => { setProveedorEditando(p);   setMostrarFormulario(true); };

  const guardarProveedor = (proveedor) => {
    if (proveedor.id) {
      setProveedores(proveedores.map(p => p.id === proveedor.id ? proveedor : p));
      mostrarAlerta('Proveedor actualizado correctamente', 'success');
    } else {
      setProveedores([...proveedores, { ...proveedor, id: Date.now() }]);
      mostrarAlerta('Proveedor guardado correctamente', 'success');
    }
    setMostrarFormulario(false);
  };

  const eliminarProveedor = (id) => {
    if (window.confirm('¿Está seguro de eliminar este proveedor?')) {
      setProveedores(proveedores.filter(p => p.id !== id));
      mostrarAlerta('Proveedor eliminado correctamente', 'success');
    }
  };

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">🚚 Proveedores</h1>
        <button className="btn btn-primary" onClick={abrirNuevo}>+ Nuevo Proveedor</button>
      </div>

      <Alerta mensaje={alerta.mensaje} tipo={alerta.tipo} />

      {mostrarFormulario && (
        <div style={{ marginBottom: '1.5rem' }}>
          <FormularioProveedor
            proveedorEditando={proveedorEditando}
            onGuardar={guardarProveedor}
            onCancelar={() => setMostrarFormulario(false)}
          />
        </div>
      )}

      <div className="table-wrapper">
        <table>
          <thead>
            <tr><th>#</th><th>Nombre</th><th>Contacto</th><th>Teléfono</th><th>Acciones</th></tr>
          </thead>
          <tbody>
            {proveedores.length === 0 ? (
              <tr><td colSpan="5" className="empty-msg">No hay proveedores registrados.</td></tr>
            ) : (
              proveedores.map((p) => (
                <tr key={p.id}>
                  <td>{p.id}</td>
                  <td><strong>{p.nombre}</strong></td>
                  <td>{p.contacto}</td>
                  <td>{p.telefono}</td>
                  <td className="acciones">
                    <button className="btn btn-warning btn-sm" onClick={() => abrirEditar(p)}>Editar</button>
                    <button className="btn btn-danger btn-sm" onClick={() => eliminarProveedor(p.id)}>Eliminar</button>
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

export default ListaProveedores;
