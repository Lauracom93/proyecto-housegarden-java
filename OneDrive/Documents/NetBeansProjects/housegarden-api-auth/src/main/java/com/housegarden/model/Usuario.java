package com.housegarden.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * ============================================================
 * ENTIDAD: Usuario
 * ============================================================
 * Clase modelo que representa un Usuario del sistema HouseGarden.
 * Mapeada a la tabla 'usuarios' de MySQL mediante JPA.
 *
 * La contraseña se almacena ENCRIPTADA con BCrypt para seguridad.
 * Nunca se almacena la contraseña en texto plano.
 *
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * ============================================================
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    /** Identificador único del usuario - clave primaria auto-incremental */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    /**
     * Nombre de usuario único para inicio de sesión.
     * No puede repetirse en la base de datos (unique = true).
     */
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, max = 50, message = "El usuario debe tener entre 3 y 50 caracteres")
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    /**
     * Contraseña del usuario almacenada con hash BCrypt.
     * Nunca se guarda ni se devuelve la contraseña en texto plano.
     */
    @NotBlank(message = "La contraseña es obligatoria")
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Rol del usuario en el sistema.
     * Ejemplo: "ADMIN", "USER"
     */
    @Column(name = "rol")
    private String rol;

    // ── Constructor vacío requerido por JPA ──────────────────
    public Usuario() {}

    // ── Getters y Setters ────────────────────────────────────
    public Integer getIdUsuario()              { return idUsuario; }
    public void setIdUsuario(Integer id)       { this.idUsuario = id; }

    public String getUsername()                { return username; }
    public void setUsername(String username)   { this.username = username; }

    public String getPassword()                { return password; }
    public void setPassword(String password)   { this.password = password; }

    public String getRol()                     { return rol; }
    public void setRol(String rol)             { this.rol = rol; }
}
