package com.housegarden;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ============================================================
 * CLASE PRINCIPAL: HouseGardenApiApplication
 * ============================================================
 * Punto de entrada de la API REST de autenticación HouseGarden.
 *
 * Esta API provee dos servicios web principales:
 *   POST /api/auth/registro  - Registro de nuevo usuario
 *   POST /api/auth/login     - Inicio de sesión
 *
 * Proyecto  : HouseGarden - API de Autenticación
 * Evidencia : GA7-220501096-AA5-EV01
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * Ficha     : 3118327 | SENA 2026
 * ============================================================
 */
@SpringBootApplication
public class HouseGardenApiApplication {

    /**
     * Método principal que inicia la API Spring Boot.
     * El servidor inicia en el puerto 8081.
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(HouseGardenApiApplication.class, args);
    }
}
