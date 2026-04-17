/**
 * ============================================================
 * COMPONENTE: FormularioOrden.js
 * ============================================================
 * Formulario controlado para crear y editar órdenes.
 * Recibe la lista de clientes como prop para el select desplegable.
 *
 * Props:
 *   @prop {Object|null} ordenEditando - Orden a editar o null si es nueva
 *   @prop {Array}       clientes      - Lista de clientes para el select
 *   @prop {Function}    onGuardar     - Función del padre para guardar
 *   @prop {Function}    onCancelar    - Función del padre para cancelar
 *
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * ============================================================
 */
import React, { useState, useEffect } from 'react';

function FormularioOrden({ ordenEditando, clientes, onGuardar, onCancelar }) {

  const [formulario, setFormulario] = useState({
    id: '', clienteNombre: '', fecha: '', estado: 'Pendiente', total: ''
  });

  useEffect(() => {
    if (ordenEditando) {
      setFormulario(ordenEditando);
    } else {
      setFormulario({ id: '', clienteNombre: '', fecha: '', estado: 'Pendiente', total: '' });
    }
  }, [ordenEditando]);

  const manejarCambio = (e) => {
    const { name, value } = e.target;
    setFormulario({ ...formulario, [name]: value });
  };

  const manejarEnvio = (e) => {
    e.preventDefault();
    if (!formulario.clienteNombre) {
      alert('Debe seleccionar un cliente');
      return;
    }
    if (Number(formulario.total) < 0) {
      alert('El total no puede ser negativo');
      return;
    }
    onGuardar({ ...formulario, total: Number(formulario.total) });
  };

  return (
    <div className="card">
      <h2 style={{ marginBottom: '1.2rem', color: '#2d5016' }}>
        {formulario.id ? '✏️ Editar Orden' : '📋 Nueva Orden'}
      </h2>
      <form onSubmit={manejarEnvio}>

        {/* Select de clientes cargado desde la prop clientes */}
        <div className="form-group">
          <label>Cliente *</label>
          <select name="clienteNombre" className="form-control"
            value={formulario.clienteNombre} onChange={manejarCambio} required>
            <option value="">-- Seleccione un cliente --</option>
            {/* Iterar la lista de clientes recibida como prop */}
            {clientes.map((c) => (
              <option key={c.id} value={c.nombre}>{c.nombre}</option>
            ))}
          </select>
        </div>

        <div className="form-group">
          <label>Fecha *</label>
          <input type="date" name="fecha" className="form-control"
            value={formulario.fecha} onChange={manejarCambio} required />
        </div>

        <div className="form-group">
          <label>Estado</label>
          <select name="estado" className="form-control"
            value={formulario.estado} onChange={manejarCambio}>
            <option value="Pendiente">Pendiente</option>
            <option value="Completado">Completado</option>
            <option value="Cancelado">Cancelado</option>
          </select>
        </div>

        <div className="form-group">
          <label>Total (COP) *</label>
          <input type="number" name="total" className="form-control"
            placeholder="Ej: 45000" value={formulario.total}
            onChange={manejarCambio} min="0" required />
        </div>

        <div className="form-actions">
          <button type="submit" className="btn btn-primary">
            💾 {formulario.id ? 'Actualizar' : 'Guardar'}
          </button>
          <button type="button" className="btn btn-secondary" onClick={onCancelar}>Cancelar</button>
        </div>
      </form>
    </div>
  );
}

export default FormularioOrden;
