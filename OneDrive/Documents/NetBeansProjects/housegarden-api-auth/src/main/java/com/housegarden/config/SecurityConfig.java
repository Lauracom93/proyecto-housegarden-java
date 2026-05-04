package com.housegarden.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * ============================================================
 * CONFIGURACIÓN: SecurityConfig
 * ============================================================
 * Configuración de seguridad de Spring Security.
 *
 * Por defecto, Spring Security bloquea todos los endpoints
 * con autenticación básica. Esta configuración permite el
 * acceso libre a los endpoints de la API de autenticación
 * ya que el control de acceso lo maneja nuestro propio servicio.
 *
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * ============================================================
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configura la cadena de filtros de seguridad.
     * Desactiva CSRF (no necesario en APIs REST stateless)
     * y permite acceso libre a todos los endpoints /auth/*.
     *
     * @param http objeto de configuración de seguridad HTTP
     * @return cadena de filtros configurada
     * @throws Exception si hay error en la configuración
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Desactivar CSRF (Cross-Site Request Forgery) para APIs REST
            .csrf(csrf -> csrf.disable())
            // Permitir todas las peticiones sin autenticación de Spring Security
            // El control de acceso lo maneja AuthService con BCrypt
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            );
        return http.build();
    }
}
