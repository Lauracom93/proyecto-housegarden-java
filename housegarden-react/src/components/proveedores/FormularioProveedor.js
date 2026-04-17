/**
 * ============================================================
 * COMPONENTE: FormularioProveedor.js
 * ============================================================
 * Formulario controlado para crear y editar proveedores.
 *
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * ============================================================
 */
import React, { useState, useEffect } from 'react';

function FormularioProveedor({ proveedorEditando, onGuardar, onCancelar }) {

  const [formulario, setFormulario] = useState({ id: '', nombre: '', contacto: '', telefono: '' });

  useEffect(() => {
    if (proveedorEditando) {
      setFormulario(proveedorEditando);
    } else {
      setFormulario({ id: '', nombre: '', contacto: '', telefono: '' });
    }
  }, [proveedorEditando]);

  const manejarCambio = (e) => {
    const { name, value } = e.target;
    setFormulario({ ...formulario, [name]: value });
  };

  const manejarEnvio = (e) => {
    e.preventDefault();
    if (!formulario.nombre.trim()) {
      alert('El nombre del proveedor es obligatorio');
      return;
    }
    onGuardar(formulario);
  };

  return (
    <div className="card">
      <h2 style={{ marginBottom: '1.2rem', color: '#2d5016' }}>
        {formulario.id ? '✏️ Editar Proveedor' : '🚚 Nuevo Proveedor'}
      </h2>
      <form onSubmit={manejarEnvio}>
        <div className="form-group">
          <label>Nombre empresa *</label>
          <input type="text" name="nombre" className="form-control"
            placeholder="Ej: Vivero Central" value={formulario.nombre}
            onChange={manejarCambio} required />
        </div>
        <div className="form-group">
          <label>Persona de contacto</label>
          <input type="text" name="contacto" className="form-control"
            placeholder="Ej: Carlos López" value={formulario.contacto}
            onChange={manejarCambio} />
        </div>
        <div className="form-group">
          <label>Teléfono</label>
          <input type="text" name="telefono" className="form-control"
            placeholder="Ej: 3004567890" value={formulario.telefono}
            onChange={manejarCambio} />
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

export default FormularioProveedor;
