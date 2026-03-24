package com.housegarden.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ============================================================
 * CONTROLADOR: InicioController
 * ============================================================
 * Controlador Spring MVC para la pagina de inicio del sistema.
 * Muestra el dashboard principal con acceso a todos los modulos.
 *
 * Aprendices: Laura Combita - Diana Amaya | Ficha: 3118327
 * ============================================================
 */
@Controller
public class InicioController {

    /**
     * GET / - Retorna la pagina principal del sistema HouseGarden.
     * Muestra el dashboard con las tarjetas de acceso a cada modulo:
     * Productos, Clientes, Proveedores y Ordenes.
     *
     * @return nombre de la plantilla Thymeleaf (templates/index.html)
     */
    @GetMapping("/")
    public String inicio() {
        // Retorna la plantilla templates/index.html
        return "index";
    }
}
