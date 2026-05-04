package com.housegarden.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * ============================================================
 * DTOs (Data Transfer Objects) para la API de autenticación
 * ============================================================
 * Clases internas estáticas que representan los datos de entrada
 * y salida de los endpoints de la API.
 *
 * Se usan DTOs en lugar de la entidad directamente para:
 *   1. No exponer campos internos (como la contraseña hasheada)
 *   2. Tener validaciones específicas por endpoint
 *   3. Separar la capa de presentación del modelo de datos
 *
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * ============================================================
 */
public class AuthDTOs {

    /**
     * DTO de solicitud de REGISTRO.
     * Contiene los datos que el cliente envía para crear una cuenta.
     * Endpoint: POST /api/auth/registro
     */
    public static class RegistroRequest {

        /** Nombre de usuario deseado - mínimo 3 caracteres */
        @NotBlank(message = "El nombre de usuario es obligatorio")
        @Size(min = 3, max = 50, message = "El usuario debe tener entre 3 y 50 caracteres")
        private String username;

        /**
         * Contraseña en texto plano - se encriptará antes de guardar.
         * Mínimo 6 caracteres por seguridad.
         */
        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener mínimo 6 caracteres")
        private String password;

        /** Rol del usuario: "ADMIN" o "USER" */
        private String rol;

        // Getters y Setters
        public String getUsername()              { return username; }
        public void setUsername(String u)        { this.username = u; }
        public String getPassword()              { return password; }
        public void setPassword(String p)        { this.password = p; }
        public String getRol()                   { return rol; }
        public void setRol(String r)             { this.rol = r; }
    }

    /**
     * DTO de solicitud de LOGIN (inicio de sesión).
     * Contiene el usuario y contraseña para autenticar.
     * Endpoint: POST /api/auth/login
     */
    public static class LoginRequest {

        /** Nombre de usuario registrado */
        @NotBlank(message = "El nombre de usuario es obligatorio")
        private String username;

        /** Contraseña en texto plano para comparar con el hash */
        @NotBlank(message = "La contraseña es obligatoria")
        private String password;

        // Getters y Setters
        public String getUsername()              { return username; }
        public void setUsername(String u)        { this.username = u; }
        public String getPassword()              { return password; }
        public void setPassword(String p)        { this.password = p; }
    }

    /**
     * DTO de respuesta de la API.
     * Estructura estándar que devuelve todos los endpoints.
     * Incluye: éxito/error, mensaje descriptivo y datos opcionales.
     */
    public static class ApiResponse {

        /** true si la operación fue exitosa, false si falló */
        private boolean exitoso;

        /** Mensaje descriptivo del resultado */
        private String mensaje;

        /** Datos adicionales opcionales (ej: información del usuario) */
        private Object datos;

        // Constructor con todos los campos
        public ApiResponse(boolean exitoso, String mensaje, Object datos) {
            this.exitoso = exitoso;
            this.mensaje = mensaje;
            this.datos   = datos;
        }

        // Constructor sin datos adicionales
        public ApiResponse(boolean exitoso, String mensaje) {
            this(exitoso, mensaje, null);
        }

        // Getters
        public boolean isExitoso()   { return exitoso; }
        public String getMensaje()   { return mensaje; }
        public Object getDatos()     { return datos; }
    }
}
