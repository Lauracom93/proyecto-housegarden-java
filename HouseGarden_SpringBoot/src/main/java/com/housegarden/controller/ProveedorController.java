package com.housegarden.controller;

import com.housegarden.model.Proveedor;
import com.housegarden.repository.ProveedorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * ============================================================
 * CONTROLADOR: ProveedorController
 * ============================================================
 * Controlador Spring MVC para la gestion del modulo de Proveedores.
 * Gestiona las empresas que suministran productos al vivero.
 * Implementa CRUD completo: Listar, Crear, Editar, Eliminar.
 *
 * Aprendices: Laura Combita - Diana Amaya | Ficha: 3118327
 * ============================================================
 */
@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    /** Repositorio JPA inyectado por Spring para acceso a datos de proveedores */
    @Autowired
    private ProveedorRepository proveedorRepo;

    /**
     * GET /proveedores - Lista todos los proveedores.
     * @param model para pasar la lista de proveedores a la vista
     * @return plantilla templates/proveedores/lista.html
     */
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proveedores", proveedorRepo.findAll());
        return "proveedores/lista";
    }

    /**
     * GET /proveedores/nuevo - Formulario para nuevo proveedor.
     * @param model con el objeto proveedor vacio para el formulario
     * @return plantilla templates/proveedores/formulario.html
     */
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "proveedores/formulario";
    }

    /**
     * GET /proveedores/editar/{id} - Formulario con datos del proveedor.
     * @param id    ID del proveedor a editar (de la URL)
     * @param model con el proveedor encontrado para pre-llenar el formulario
     * @return plantilla del formulario o redireccion si no existe
     */
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Proveedor proveedor = proveedorRepo.findById(id).orElse(null);
        if (proveedor == null) return "redirect:/proveedores";
        model.addAttribute("proveedor", proveedor);
        return "proveedores/formulario";
    }

    /**
     * POST /proveedores/guardar - Guarda el proveedor en la base de datos.
     * @param proveedor objeto con los datos del formulario HTML
     * @param resultado errores de validacion de Bean Validation
     * @param flash     mensaje de exito para mostrar tras el redirect
     * @return redireccion al listado o formulario si hay errores
     */
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Proveedor proveedor,
                          BindingResult resultado,
                          RedirectAttributes flash) {
        if (resultado.hasErrors()) return "proveedores/formulario";
        proveedorRepo.save(proveedor);
        flash.addFlashAttribute("msg", "Proveedor guardado correctamente");
        return "redirect:/proveedores";
    }

    /**
     * GET /proveedores/eliminar/{id} - Elimina un proveedor.
     * @param id    ID del proveedor a eliminar
     * @param flash mensaje de confirmacion
     * @return redireccion al listado
     */
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes flash) {
        proveedorRepo.deleteById(id);
        flash.addFlashAttribute("msg", "Proveedor eliminado correctamente");
        return "redirect:/proveedores";
    }
}
