package com.housegarden.service;

import com.housegarden.model.AuthDTOs;
import com.housegarden.model.Usuario;
import com.housegarden.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * ============================================================
 * SERVICIO: AuthService
 * ============================================================
 * Clase de servicio que contiene la lógica de negocio para
 * el registro e inicio de sesión de usuarios.
 *
 * Responsabilidades:
 *   1. REGISTRO: validar datos, verificar usuario único,
 *      encriptar contraseña con BCrypt y guardar en MySQL.
 *   2. LOGIN: buscar usuario, comparar contraseña con BCrypt,
 *      retornar respuesta de éxito o error.
 *
 * Seguridad:
 *   - Las contraseñas se encriptan con BCrypt (hash irreversible)
 *   - Nunca se almacena ni devuelve la contraseña en texto plano
 *   - BCryptPasswordEncoder.matches() compara sin desencriptar
 *
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * Ficha: 3118327 | SENA 2026
 * ============================================================
 */
@Service
public class AuthService {

    /** Repositorio para operaciones CRUD sobre la tabla usuarios */
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Codificador BCrypt para encriptar y verificar contraseñas.
     * BCrypt es un algoritmo de hashing seguro que incluye un "salt"
     * aleatorio para que dos contraseñas iguales generen hashes distintos.
     */
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * Proceso:
     *   1. Verificar que el username no esté ya registrado
     *   2. Encriptar la contraseña con BCrypt
     *   3. Guardar el usuario en la base de datos MySQL
     *   4. Retornar respuesta de éxito o error
     *
     * @param request DTO con username, password y rol del nuevo usuario
     * @return ApiResponse con el resultado del registro
     */
    public AuthDTOs.ApiResponse registrar(AuthDTOs.RegistroRequest request) {

        // Verificar si el nombre de usuario ya está en uso
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            return new AuthDTOs.ApiResponse(
                false,
                "Error en el registro: el nombre de usuario '" + request.getUsername() + "' ya está registrado."
            );
        }

        // Crear nuevo objeto Usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(request.getUsername());

        // Encriptar la contraseña antes de guardarla en la BD
        // encode() genera un hash BCrypt seguro con salt aleatorio
        String passwordEncriptada = passwordEncoder.encode(request.getPassword());
        nuevoUsuario.setPassword(passwordEncriptada);

        // Asignar rol (USER por defecto si no se especifica)
        nuevoUsuario.setRol(request.getRol() != null ? request.getRol() : "USER");

        // Guardar en MySQL usando Spring Data JPA
        usuarioRepository.save(nuevoUsuario);

        // Retornar respuesta exitosa (sin incluir la contraseña)
        return new AuthDTOs.ApiResponse(
            true,
            "✔ Registro exitoso. Usuario '" + nuevoUsuario.getUsername() + "' creado correctamente.",
            new InfoUsuario(nuevoUsuario.getIdUsuario(), nuevoUsuario.getUsername(), nuevoUsuario.getRol())
        );
    }

    /**
     * Autentica un usuario (inicio de sesión).
     *
     * Proceso:
     *   1. Buscar el usuario por username en la base de datos
     *   2. Si no existe: retornar error de autenticación
     *   3. Comparar la contraseña ingresada con el hash BCrypt guardado
     *   4. Si coinciden: autenticación satisfactoria
     *   5. Si no coinciden: error en la autenticación
     *
     * @param request DTO con username y password del usuario
     * @return ApiResponse con mensaje de éxito o error de autenticación
     */
    public AuthDTOs.ApiResponse login(AuthDTOs.LoginRequest request) {

        // Buscar el usuario por nombre de usuario en la base de datos
        Usuario usuario = usuarioRepository
                .findByUsername(request.getUsername())
                .orElse(null);

        // Si el usuario no existe en la BD
        if (usuario == null) {
            return new AuthDTOs.ApiResponse(
                false,
                "✖ Error en la autenticación: usuario no encontrado."
            );
        }

        // Comparar la contraseña ingresada con el hash BCrypt almacenado.
        // matches() desencripta internamente sin exponer la contraseña real.
        boolean passwordCorrecta = passwordEncoder.matches(
            request.getPassword(),  // contraseña en texto plano (ingresada)
            usuario.getPassword()   // hash BCrypt almacenado en MySQL
        );

        if (passwordCorrecta) {
            // Autenticación exitosa
            return new AuthDTOs.ApiResponse(
                true,
                "✔ Autenticación satisfactoria. Bienvenido, " + usuario.getUsername() + ".",
                new InfoUsuario(usuario.getIdUsuario(), usuario.getUsername(), usuario.getRol())
            );
        } else {
            // Contraseña incorrecta
            return new AuthDTOs.ApiResponse(
                false,
                "✖ Error en la autenticación: contraseña incorrecta."
            );
        }
    }

    /**
     * Clase interna para retornar información del usuario en la respuesta.
     * No incluye la contraseña por seguridad.
     */
    public static class InfoUsuario {
        private Integer id;
        private String username;
        private String rol;

        public InfoUsuario(Integer id, String username, String rol) {
            this.id       = id;
            this.username = username;
            this.rol      = rol;
        }

        public Integer getId()     { return id; }
        public String getUsername(){ return username; }
        public String getRol()     { return rol; }
    }
}
