/**
 * ============================================================
 * COMPONENTE: Alerta.js
 * ============================================================
 * Componente reutilizable para mostrar mensajes de éxito o error
 * después de realizar operaciones CRUD en cualquier módulo.
 *
 * Props recibidas:
 *   @prop {string} mensaje - Texto del mensaje a mostrar
 *   @prop {string} tipo    - Tipo de alerta: 'success' o 'danger'
 *
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * ============================================================
 */
import React from 'react';

/**
 * Componente funcional Alerta.
 * Se muestra solo cuando hay un mensaje (si mensaje está vacío, no renderiza nada).
 *
 * @param {Object} props
 * @param {string} props.mensaje - Texto a mostrar en la alerta
 * @param {string} props.tipo    - 'success' (verde) o 'danger' (rojo)
 * @returns {JSX.Element|null} Alerta visible o null si no hay mensaje
 */
function Alerta({ mensaje, tipo }) {
  // Si no hay mensaje, no renderizar nada
  if (!mensaje) return null;

  return (
    <div className={`alert alert-${tipo}`}>
      {tipo === 'success' ? '✔ ' : '⚠ '}
      {mensaje}
    </div>
  );
}

export default Alerta;
