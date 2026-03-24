package com.housegarden.controller;

import com.housegarden.model.Orden;
import com.housegarden.repository.ClienteRepository;
import com.housegarden.repository.OrdenRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * ============================================================
 * CONTROLADOR: OrdenController
 * ============================================================
 * Controlador Spring MVC para la gestion del modulo de Ordenes.
 * Gestiona los pedidos de compra realizados por los clientes.
 * Implementa CRUD completo: Listar, Crear, Editar, Eliminar.
 *
 * Caracteristica especial: El formulario carga la lista de clientes
 * desde la base de datos para mostrar un campo select desplegable,
 * en lugar de un campo de texto para ingresar el ID manualmente.
 *
 * Aprendices: Laura Combita - Diana Amaya | Ficha: 3118327
 * ============================================================
 */
@Controller
@RequestMapping("/ordenes")
public class OrdenController {

    /** Repositorio JPA para operaciones CRUD de ordenes */
    @Autowired
    private OrdenRepository ordenRepo;

    /**
     * Repositorio de clientes para cargar la lista desplegable.
     * Se usa en el formulario para seleccionar el cliente de la orden.
     */
    @Autowired
    private ClienteRepository clienteRepo;

    /**
     * GET /ordenes - Lista todas las ordenes de compra.
     * @param model para pasar la lista de ordenes a la vista Thymeleaf
     * @return plantilla templates/ordenes/lista.html
     */
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ordenes", ordenRepo.findAll());
        return "ordenes/lista";
    }

    /**
     * GET /ordenes/nuevo - Formulario para registrar nueva orden.
     * Carga la lista de clientes para el campo select desplegable.
     *
     * @param model con la orden vacia y la lista de clientes
     * @return plantilla templates/ordenes/formulario.html
     */
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        // Objeto orden vacio para el formulario Thymeleaf
        model.addAttribute("orden", new Orden());
        // Cargar todos los clientes para el select desplegable del formulario
        model.addAttribute("clientes", clienteRepo.findAll());
        return "ordenes/formulario";
    }

    /**
     * GET /ordenes/editar/{id} - Carga datos de la orden para edicion.
     * @param id    ID de la orden a editar (capturado de la URL)
     * @param model con la orden encontrada y la lista de clientes
     * @return plantilla del formulario o redireccion si no existe
     */
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        // Buscar la orden por ID; redirigir al listado si no existe
        Orden orden = ordenRepo.findById(id).orElse(null);
        if (orden == null) return "redirect:/ordenes";
        model.addAttribute("orden", orden);
        // Cargar clientes para el select desplegable en modo edicion
        model.addAttribute("clientes", clienteRepo.findAll());
        return "ordenes/formulario";
    }

    /**
     * POST /ordenes/guardar - Guarda la orden en la base de datos.
     * Si la orden tiene ID es actualizacion; si no tiene ID es insercion nueva.
     *
     * @param orden     objeto orden con los datos del formulario HTML
     * @param resultado errores de validacion de Bean Validation
     * @param model     para volver al formulario con datos si hay errores
     * @param flash     mensaje de exito para mostrar tras el redirect
     * @return redireccion al listado o formulario si hay errores de validacion
     */
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Orden orden,
                          BindingResult resultado,
                          Model model,
                          RedirectAttributes flash) {
        // Si hay errores, volver al formulario y recargar la lista de clientes
        if (resultado.hasErrors()) {
            model.addAttribute("clientes", clienteRepo.findAll());
            return "ordenes/formulario";
        }
        // Guardar en MySQL (Spring Data JPA genera el SQL automaticamente)
        ordenRepo.save(orden);
        flash.addFlashAttribute("msg", "Orden guardada correctamente");
        return "redirect:/ordenes";
    }

    /**
     * GET /ordenes/eliminar/{id} - Elimina una orden de la base de datos.
     * @param id    ID de la orden a eliminar
     * @param flash mensaje de confirmacion de eliminacion
     * @return redireccion al listado de ordenes
     */
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes flash) {
        ordenRepo.deleteById(id);
        flash.addFlashAttribute("msg", "Orden eliminada correctamente");
        return "redirect:/ordenes";
    }
}
