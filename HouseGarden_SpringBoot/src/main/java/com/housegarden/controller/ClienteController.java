package com.housegarden.controller;

import com.housegarden.model.Cliente;
import com.housegarden.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * ============================================================
 * CONTROLADOR: ClienteController
 * ============================================================
 * Controlador Spring MVC para la gestion del modulo de Clientes.
 * Implementa las operaciones CRUD completas:
 *   - Listar   : GET  /clientes
 *   - Crear    : GET  /clientes/nuevo  +  POST /clientes/guardar
 *   - Editar   : GET  /clientes/editar/{id}  +  POST /clientes/guardar
 *   - Eliminar : GET  /clientes/eliminar/{id}
 *
 * Aprendices: Laura Combita - Diana Amaya | Ficha: 3118327
 * ============================================================
 */
@Controller
@RequestMapping("/clientes")
public class ClienteController {

    /**
     * Repositorio JPA inyectado por Spring para acceso a datos de clientes.
     * Provee metodos CRUD sin necesidad de escribir SQL.
     */
    @Autowired
    private ClienteRepository clienteRepo;

    /**
     * GET /clientes - Lista todos los clientes de la base de datos.
     * @param model objeto Model para pasar la lista a la vista Thymeleaf
     * @return plantilla templates/clientes/lista.html
     */
    @GetMapping
    public String listar(Model model) {
        // findAll() de JpaRepository obtiene todos los registros de la tabla clientes
        model.addAttribute("clientes", clienteRepo.findAll());
        return "clientes/lista";
    }

    /**
     * GET /clientes/nuevo - Muestra formulario vacio para nuevo cliente.
     * @param model objeto Model con el cliente vacio para el formulario
     * @return plantilla templates/clientes/formulario.html
     */
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        // Objeto vacio para vincular con th:object en el formulario Thymeleaf
        model.addAttribute("cliente", new Cliente());
        return "clientes/formulario";
    }

    /**
     * GET /clientes/editar/{id} - Carga datos del cliente para edicion.
     * @param id    ID del cliente a editar capturado de la URL
     * @param model objeto Model con el cliente encontrado
     * @return plantilla del formulario o redireccion si no existe el cliente
     */
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        // Buscar cliente; si no existe redirigir al listado
        Cliente cliente = clienteRepo.findById(id).orElse(null);
        if (cliente == null) return "redirect:/clientes";
        model.addAttribute("cliente", cliente);
        return "clientes/formulario";
    }

    /**
     * POST /clientes/guardar - Guarda el cliente (nuevo o editado).
     * Thymeleaf vincula los campos del formulario HTML al objeto Cliente.
     * @Valid activa las validaciones definidas con anotaciones en la entidad.
     *
     * @param cliente   objeto Cliente con los datos del formulario
     * @param resultado errores de validacion detectados por @Valid
     * @param flash     para pasar mensaje de exito despues del redirect
     * @return redireccion al listado o vuelta al formulario si hay errores
     */
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Cliente cliente,
                          BindingResult resultado,
                          RedirectAttributes flash) {
        // Si hay errores de validacion, mostrar formulario con mensajes de error
        if (resultado.hasErrors()) return "clientes/formulario";
        // save() inserta si no tiene ID, actualiza si ya tiene ID
        clienteRepo.save(cliente);
        flash.addFlashAttribute("msg", "Cliente guardado correctamente");
        return "redirect:/clientes";
    }

    /**
     * GET /clientes/eliminar/{id} - Elimina un cliente de la base de datos.
     * @param id    ID del cliente a eliminar
     * @param flash mensaje de confirmacion para mostrar en el listado
     * @return redireccion al listado de clientes
     */
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes flash) {
        clienteRepo.deleteById(id);
        flash.addFlashAttribute("msg", "Cliente eliminado correctamente");
        return "redirect:/clientes";
    }
}
