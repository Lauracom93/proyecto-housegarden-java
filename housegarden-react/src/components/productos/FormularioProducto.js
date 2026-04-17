/**
 * ============================================================
 * COMPONENTE: FormularioProducto.js
 * ============================================================
 * Formulario controlado para crear y editar productos.
 * Patrón de formulario controlado de React:
 *   - Cada campo está vinculado al estado con useState
 *   - onChange actualiza el estado en tiempo real
 *   - onSubmit valida y llama al padre mediante props
 *
 * Props recibidas:
 *   @prop {Object|null} productoEditando - Producto a editar o null si es nuevo
 *   @prop {Function}    onGuardar        - Función del padre para guardar
 *   @prop {Function}    onCancelar       - Función del padre para cancelar
 *
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * ============================================================
 */
import React, { useState, useEffect } from 'react';

function FormularioProducto({ productoEditando, onGuardar, onCancelar }) {

  // Estado del formulario: todos los campos del producto
  const [formulario, setFormulario] = useState({
    id: '', nombre: '', descripcion: '', precio: '', stock: '', categoria: ''
  });

  /**
   * useEffect: cuando cambia productoEditando, actualiza el formulario.
   * Si hay producto a editar, pre-llena los campos.
   * Si es null (nuevo), limpia el formulario.
   */
  useEffect(() => {
    if (productoEditando) {
      setFormulario(productoEditando);
    } else {
      setFormulario({ id: '', nombre: '', descripcion: '', precio: '', stock: '', categoria: '' });
    }
  }, [productoEditando]);

  /**
   * Manejador único para todos los campos del formulario.
   * Usa el atributo name para identificar qué campo cambió.
   * @param {Event} e - Evento onChange del input/select/textarea
   */
  const manejarCambio = (e) => {
    const { name, value } = e.target;
    setFormulario({ ...formulario, [name]: value });
  };

  /**
   * Maneja el envío del formulario.
   * Valida campos obligatorios y llama a onGuardar del padre.
   * @param {Event} e - Evento onSubmit del formulario
   */
  const manejarEnvio = (e) => {
    e.preventDefault();
    if (!formulario.nombre.trim()) {
      alert('El nombre del producto es obligatorio');
      return;
    }
    if (Number(formulario.precio) < 0) {
      alert('El precio no puede ser negativo');
      return;
    }
    // Enviar datos al componente padre mediante la prop onGuardar
    onGuardar({ ...formulario, precio: Number(formulario.precio), stock: Number(formulario.stock) });
  };

  return (
    <div className="card">
      <h2 style={{ marginBottom: '1.2rem', color: '#2d5016' }}>
        {formulario.id ? '✏️ Editar Producto' : '🌱 Nuevo Producto'}
      </h2>
      <form onSubmit={manejarEnvio}>
        <div className="form-group">
          <label>Nombre del Producto *</label>
          <input type="text" name="nombre" className="form-control"
            placeholder="Ej: Rosa Roja" value={formulario.nombre}
            onChange={manejarCambio} required />
        </div>
        <div className="form-group">
          <label>Descripción</label>
          <textarea name="descripcion" className="form-control"
            placeholder="Descripción del producto..." value={formulario.descripcion}
            onChange={manejarCambio} rows="2" />
        </div>
        <div className="form-group">
          <label>Precio (COP) *</label>
          <input type="number" name="precio" className="form-control"
            placeholder="Ej: 15000" value={formulario.precio}
            onChange={manejarCambio} min="0" required />
        </div>
        <div className="form-group">
          <label>Stock *</label>
          <input type="number" name="stock" className="form-control"
            placeholder="Ej: 20" value={formulario.stock}
            onChange={manejarCambio} min="0" required />
        </div>
        <div className="form-group">
          <label>Categoría</label>
          <select name="categoria" className="form-control"
            value={formulario.categoria} onChange={manejarCambio}>
            <option value="">-- Seleccione --</option>
            <option value="Plantas">Plantas</option>
            <option value="Herramientas">Herramientas</option>
            <option value="Fertilizantes">Fertilizantes</option>
          </select>
        </div>
        <div className="form-actions">
          <button type="submit" className="btn btn-primary">
            💾 {formulario.id ? 'Actualizar' : 'Guardar'}
          </button>
          <button type="button" className="btn btn-secondary" onClick={onCancelar}>
            Cancelar
          </button>
        </div>
      </form>
    </div>
  );
}

export default FormularioProducto;
