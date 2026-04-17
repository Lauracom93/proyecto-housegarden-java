/**
 * ============================================================
 * COMPONENTE: FormularioCliente.js
 * ============================================================
 * Formulario controlado para crear y editar clientes.
 * Incluye validación de formato de correo electrónico.
 *
 * Props: productoEditando, onGuardar, onCancelar
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * ============================================================
 */
import React, { useState, useEffect } from 'react';

function FormularioCliente({ clienteEditando, onGuardar, onCancelar }) {

  // Estado del formulario con los campos del cliente
  const [formulario, setFormulario] = useState({
    id: '', nombre: '', correo: '', telefono: '', fechaRegistro: ''
  });

  // pre-llena el formulario cuando hay un cliente a editar
  useEffect(() => {
    if (clienteEditando) {
      setFormulario(clienteEditando);
    } else {
      setFormulario({ id: '', nombre: '', correo: '', telefono: '', fechaRegistro: '' });
    }
  }, [clienteEditando]);

  // Manejador único para todos los campos
  const manejarCambio = (e) => {
    const { name, value } = e.target;
    setFormulario({ ...formulario, [name]: value });
  };

  const manejarEnvio = (e) => {
    e.preventDefault();
    // Validación: nombre obligatorio
    if (!formulario.nombre.trim()) {
      alert('El nombre del cliente es obligatorio');
      return;
    }
    // Validación básica de correo
    if (formulario.correo && !formulario.correo.includes('@')) {
      alert('El correo no tiene un formato válido');
      return;
    }
    onGuardar(formulario);
  };

  return (
    <div className="card">
      <h2 style={{ marginBottom: '1.2rem', color: '#2d5016' }}>
        {formulario.id ? '✏️ Editar Cliente' : '👤 Nuevo Cliente'}
      </h2>
      <form onSubmit={manejarEnvio}>
        <div className="form-group">
          <label>Nombre completo *</label>
          <input type="text" name="nombre" className="form-control"
            placeholder="Ej: Juan Pérez" value={formulario.nombre}
            onChange={manejarCambio} required />
        </div>
        <div className="form-group">
          <label>Correo electrónico *</label>
          <input type="email" name="correo" className="form-control"
            placeholder="Ej: juan@email.com" value={formulario.correo}
            onChange={manejarCambio} required />
        </div>
        <div className="form-group">
          <label>Teléfono</label>
          <input type="text" name="telefono" className="form-control"
            placeholder="Ej: 3001234567" value={formulario.telefono}
            onChange={manejarCambio} />
        </div>
        <div className="form-group">
          <label>Fecha de Registro *</label>
          <input type="date" name="fechaRegistro" className="form-control"
            value={formulario.fechaRegistro} onChange={manejarCambio} required />
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

export default FormularioCliente;
