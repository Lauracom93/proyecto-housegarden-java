package com.housegarden.controller;

import com.housegarden.model.AuthDTOs;
import com.housegarden.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ============================================================
 * CONTROLADOR: AuthController
 * ============================================================
 * Controlador REST que expone los endpoints de autenticación.
 * Define los servicios web de registro e inicio de sesión.
 *
 * Anotaciones REST usadas:
 *   @RestController  - Indica que esta clase es un controlador REST.
 *                      Combina @Controller + @ResponseBody para que
 *                      los métodos retornen JSON automáticamente.
 *   @RequestMapping  - URL base para todos los endpoints: /auth
 *   @PostMapping     - Mapea peticiones HTTP POST
 *   @RequestBody     - Convierte el JSON del request al objeto Java
 *   @Valid           - Activa las validaciones de Bean Validation
 *   @CrossOrigin     - Permite peticiones desde otros dominios (CORS)
 *                      Necesario para que React pueda consumir la API
 *
 * ENDPOINTS disponibles:
 *   POST /api/auth/registro  - Registrar nuevo usuario
 *   POST /api/auth/login     - Iniciar sesión
 *   GET  /api/auth/ping      - Verificar que la API está activa
 *
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * Ficha: 3118327 | SENA 2026
 * Evidencia: GA7-220501096-AA5-EV01
 * ============================================================
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*") // Permite peticiones desde React (localhost:3000)
public class AuthController {

    /**
     * Servicio de autenticación inyectado por Spring.
     * Contiene la lógica de negocio de registro y login.
     */
    @Autowired
    private AuthService authService;

    /**
     * ── ENDPOINT 1: REGISTRO ────────────────────────────────
     * POST /api/auth/registro
     *
     * Registra un nuevo usuario en el sistema HouseGarden.
     * Recibe los datos en formato JSON en el cuerpo de la petición.
     *
     * Ejemplo de body JSON para Postman:
     * {
     *   "username": "laura123",
     *   "password": "miClave123",
     *   "rol": "USER"
     * }
     *
     * Respuesta exitosa (201 Created):
     * {
     *   "exitoso": true,
     *   "mensaje": "✔ Registro exitoso. Usuario 'laura123' creado correctamente.",
     *   "datos": { "id": 1, "username": "laura123", "rol": "USER" }
     * }
     *
     * Respuesta error (400 Bad Request):
     * {
     *   "exitoso": false,
     *   "mensaje": "Error en el registro: el nombre de usuario ya está registrado."
     * }
     *
     * @param request DTO con los datos del nuevo usuario (del body JSON)
     * @return ResponseEntity con el resultado del registro y código HTTP
     */
    @PostMapping("/registro")
    public ResponseEntity<AuthDTOs.ApiResponse> registro(
            @Valid @RequestBody AuthDTOs.RegistroRequest request) {

        // Delegar la lógica de negocio al servicio AuthService
        AuthDTOs.ApiResponse respuesta = authService.registrar(request);

        // Retornar código HTTP 201 (Created) si fue exitoso, 400 si falló
        HttpStatus status = respuesta.isExitoso()
                ? HttpStatus.CREATED       // 201 - recurso creado exitosamente
                : HttpStatus.BAD_REQUEST;  // 400 - datos inválidos o duplicados

        return ResponseEntity.status(status).body(respuesta);
    }

    /**
     * ── ENDPOINT 2: LOGIN ───────────────────────────────────
     * POST /api/auth/login
     *
     * Autentica un usuario existente en el sistema HouseGarden.
     * Verifica usuario y contraseña contra la base de datos MySQL.
     *
     * Ejemplo de body JSON para Postman:
     * {
     *   "username": "laura123",
     *   "password": "miClave123"
     * }
     *
     * Respuesta exitosa (200 OK):
     * {
     *   "exitoso": true,
     *   "mensaje": "✔ Autenticación satisfactoria. Bienvenido, laura123.",
     *   "datos": { "id": 1, "username": "laura123", "rol": "USER" }
     * }
     *
     * Respuesta error (401 Unauthorized):
     * {
     *   "exitoso": false,
     *   "mensaje": "✖ Error en la autenticación: contraseña incorrecta."
     * }
     *
     * @param request DTO con username y password (del body JSON)
     * @return ResponseEntity con resultado de autenticación y código HTTP
     */
    @PostMapping("/login")
    public ResponseEntity<AuthDTOs.ApiResponse> login(
            @Valid @RequestBody AuthDTOs.LoginRequest request) {

        // Delegar la autenticación al servicio AuthService
        AuthDTOs.ApiResponse respuesta = authService.login(request);

        // Retornar código HTTP 200 si fue exitoso, 401 si falló
        HttpStatus status = respuesta.isExitoso()
                ? HttpStatus.OK           // 200 - autenticación exitosa
                : HttpStatus.UNAUTHORIZED; // 401 - credenciales incorrectas

        return ResponseEntity.status(status).body(respuesta);
    }

    /**
     * ── ENDPOINT 3: PING ────────────────────────────────────
     * GET /api/auth/ping
     *
     * Endpoint de verificación para confirmar que la API está activa.
     * Útil para pruebas iniciales en Postman antes de probar los demás.
     *
     * Respuesta (200 OK):
     * {
     *   "exitoso": true,
     *   "mensaje": "🌿 HouseGarden API activa - Puerto 8081"
     * }
     *
     * @return ResponseEntity con mensaje de confirmación
     */
    @GetMapping("/ping")
    public ResponseEntity<AuthDTOs.ApiResponse> ping() {
        return ResponseEntity.ok(
            new AuthDTOs.ApiResponse(true, "🌿 HouseGarden API activa - Puerto 8081")
        );
    }
}
