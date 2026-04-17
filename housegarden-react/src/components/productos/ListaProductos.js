/**
 * ============================================================
 * COMPONENTE: ListaProductos.js
 * ============================================================
 * Componente principal del módulo de Productos.
 * Sigue el patrón visto en clase (sesión 9 - 15/04/26):
 *   - useState para manejar la lista de productos y el formulario
 *   - Componente FormularioProducto para capturar datos
 *   - Comunicación padre->hijo mediante props
 *
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * Ficha: 3118327 | SENA 2026
 * ============================================================
 */
import React, { useState } from 'react';
import FormularioProducto from './FormularioProducto';
import Alerta from '../Alerta';

function ListaProductos() {

  // Estado: lista de productos (datos de ejemplo precargados)
  const [productos, setProductos] = useState([
    { id: 1, nombre: 'Rosa Roja',      descripcion: 'Planta ornamental', precio: 15000, stock: 20, categoria: 'Plantas' },
    { id: 2, nombre: 'Pala de jardín', descripcion: 'Herramienta acero', precio: 25000, stock: 10, categoria: 'Herramientas' },
    { id: 3, nombre: 'Orquídea',       descripcion: 'Planta tropical',   precio: 35000, stock: 8,  categoria: 'Plantas' },
  ]);

  // Estado: controla si el formulario está visible
  const [mostrarFormulario, setMostrarFormulario] = useState(false);

  // Estado: producto seleccionado para editar (null = nuevo producto)
  const [productoEditando, setProductoEditando] = useState(null);

  // Estado: mensaje de alerta
  const [alerta, setAlerta] = useState({ mensaje: '', tipo: '' });

  // Muestra alerta y la oculta automáticamente después de 3 segundos
  const mostrarAlerta = (mensaje, tipo) => {
    setAlerta({ mensaje, tipo });
    setTimeout(() => setAlerta({ mensaje: '', tipo: '' }), 3000);
  };

  // Abre formulario para nuevo producto
  const abrirNuevo = () => {
    setProductoEditando(null);
    setMostrarFormulario(true);
  };

  // Abre formulario con datos del producto a editar
  const abrirEditar = (producto) => {
    setProductoEditando(producto);
    setMostrarFormulario(true);
  };

  /**
   * Guarda un producto nuevo o actualiza uno existente.
   * Se pasa como prop (onGuardar) al componente FormularioProducto.
   * @param {Object} producto - Datos del producto del formulario
   */
  const guardarProducto = (producto) => {
    if (producto.id) {
      // Actualizar: reemplazar el producto en la lista
      setProductos(productos.map(p => p.id === producto.id ? producto : p));
      mostrarAlerta('Producto actualizado correctamente', 'success');
    } else {
      // Insertar: agregar nuevo producto con id único
      setProductos([...productos, { ...producto, id: Date.now() }]);
      mostrarAlerta('Producto guardado correctamente', 'success');
    }
    setMostrarFormulario(false);
  };

  /**
   * Elimina un producto de la lista previa confirmación.
   * @param {number} id - ID del producto a eliminar
   */
  const eliminarProducto = (id) => {
    if (window.confirm('¿Está seguro de eliminar este producto?')) {
      setProductos(productos.filter(p => p.id !== id));
      mostrarAlerta('Producto eliminado correctamente', 'success');
    }
  };

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">🌱 Productos</h1>
        <button className="btn btn-primary" onClick={abrirNuevo}>
          + Nuevo Producto
        </button>
      </div>

      {/* Alerta de éxito o error */}
      <Alerta mensaje={alerta.mensaje} tipo={alerta.tipo} />

      {/* Formulario visible solo cuando mostrarFormulario es true */}
      {mostrarFormulario && (
        <div style={{ marginBottom: '1.5rem' }}>
          <FormularioProducto
            productoEditando={productoEditando}
            onGuardar={guardarProducto}
            onCancelar={() => setMostrarFormulario(false)}
          />
        </div>
      )}

      {/* Tabla de productos */}
      <div className="table-wrapper">
        <table>
          <thead>
            <tr>
              <th>#</th><th>Nombre</th><th>Descripción</th>
              <th>Precio</th><th>Stock</th><th>Categoría</th><th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {productos.length === 0 ? (
              <tr><td colSpan="7" className="empty-msg">No hay productos registrados.</td></tr>
            ) : (
              productos.map((p) => (
                <tr key={p.id}>
                  <td>{p.id}</td>
                  <td><strong>{p.nombre}</strong></td>
                  <td>{p.descripcion}</td>
                  <td>${p.precio.toLocaleString()}</td>
                  <td>{p.stock}</td>
                  <td>{p.categoria}</td>
                  <td className="acciones">
                    <button className="btn btn-warning btn-sm" onClick={() => abrirEditar(p)}>Editar</button>
                    <button className="btn btn-danger btn-sm" onClick={() => eliminarProducto(p.id)}>Eliminar</button>
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

export default ListaProductos;
