package com.housegarden;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ============================================================
 * CLASE PRINCIPAL - HouseGarden Spring Boot Application
 * ============================================================
 * Punto de entrada de la aplicacion Spring Boot.
 * La anotacion @SpringBootApplication combina tres anotaciones:
 *   - @Configuration     : Clase de configuracion de Spring
 *   - @EnableAutoConfiguration : Configura automaticamente los
 *     componentes segun las dependencias del pom.xml
 *   - @ComponentScan     : Escanea el paquete com.housegarden
 *     buscando @Controller, @Service, @Repository, etc.
 *
 * Proyecto  : Sistema de Gestion de Productos para Vivero
 * Framework : Spring Boot 3.2.3 + Spring Data JPA + Thymeleaf
 * Evidencia : GA7-220501096-AA3-EV01
 * Aprendices: Laura Angelica Combita - Diana Carina Amaya
 * Ficha     : 3118327 | SENA - Analisis y Desarrollo de Software
 * Fecha     : Marzo 2026
 * ============================================================
 */
@SpringBootApplication
public class HouseGardenApplication {

    /**
     * Metodo principal que inicia la aplicacion Spring Boot.
     * Spring Boot configura automaticamente:
     *   - El servidor Tomcat embebido en el puerto 8080
     *   - La conexion a la base de datos MySQL (HikariCP pool)
     *   - El motor de plantillas Thymeleaf
     *   - El contexto JPA con Hibernate como implementacion
     *
     * @param args argumentos de linea de comandos (no requeridos)
     */
    public static void main(String[] args) {
        SpringApplication.run(HouseGardenApplication.class, args);
    }
}
