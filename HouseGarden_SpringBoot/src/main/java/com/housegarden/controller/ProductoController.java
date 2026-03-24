package com.housegarden.controller;

import com.housegarden.model.Producto;
import com.housegarden.repository.CategoriaRepository;
import com.housegarden.repository.ProductoRepository;
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
 * CONTROLADOR: ProductoController
 * ============================================================
 * Controlador Spring MVC para la gestion del modulo de Productos.
 * Maneja todas las peticiones HTTP del modulo de productos.
 *
 * Anotaciones utilizadas:
 *   @Controller      - Marca esta clase como controlador Spring MVC
 *   @RequestMapping  - URL base para todas las rutas de este controlador
 *   @GetMapping      - Mapea peticiones HTTP GET
 *   @PostMapping     - Mapea peticiones HTTP POST
 *   @Autowired       - Inyeccion de dependencias automatica de Spring
 *   @PathVariable    - Captura variables de la URL (ej: /editar/{id})
 *   @ModelAttribute  - Vincula los datos del formulario HTML al objeto Java
 *   @Valid           - Activa la validacion de Bean Validation en el objeto
 *
 * Patron de diseno: MVC (Modelo - Vista - Controlador)
 *   Modelo   : Entidad Producto + ProductoRepository
 *   Vista    : Plantillas Thymeleaf en templates/productos/
 *   Controlador: Esta clase
 *
 * Aprendices: Laura Combita - Diana Amaya | Ficha: 3118327
 * Evidencia : GA7-220501096-AA3-EV01
 * ============================================================
 */
@Controller
@RequestMapping("/productos")
public class ProductoController {

    /**
     * Repositorio JPA para operaciones CRUD de productos.
     * Spring inyecta automaticamente la implementacion generada.
     */
    @Autowired
    private ProductoRepository productoRepo;

    /**
     * Repositorio de categorias para cargar el select del formulario.
     * Permite al usuario seleccionar la categoria del producto.
     */
    @Autowired
    private CategoriaRepository categoriaRepo;

    /**
     * Repositorio de proveedores para cargar el select del formulario.
     * Permite al usuario seleccionar el proveedor del producto.
     */
    @Autowired
    private ProveedorRepository proveedorRepo;

    /**
     * GET /productos
     * Lista todos los productos registrados en la base de datos.
     * Retorna la vista Thymeleaf 'templates/productos/lista.html'.
     *
     * @param model objeto Model de Spring para pasar datos a la vista
     * @return nombre de la plantilla Thymeleaf a renderizar
     */
    @GetMapping
    public String listar(Model model) {
        // Obtener todos los productos de la BD usando Spring Data JPA
        model.addAttribute("productos", productoRepo.findAll());
        // Retorna la plantilla templates/productos/lista.html
        return "productos/lista";
    }

    /**
     * GET /productos/nuevo
     * Muestra el formulario vacio para crear un nuevo producto.
     * Carga las listas de categorias y proveedores para los selects.
     *
     * @param model objeto Model para pasar datos a la vista
     * @return nombre de la plantilla del formulario
     */
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        // Objeto vacio para vincular con el formulario Thymeleaf (th:object)
        model.addAttribute("producto", new Producto());
        // Cargar categorias para el campo select del formulario
        model.addAttribute("categorias", categoriaRepo.findAll());
        // Cargar proveedores para el campo select del formulario
        model.addAttribute("proveedores", proveedorRepo.findAll());
        return "productos/formulario";
    }

    /**
     * GET /productos/editar/{id}
     * Muestra el formulario con los datos del producto a editar.
     * Si el ID no existe, redirige al listado de productos.
     *
     * @param id    identificador del producto a editar (de la URL)
     * @param model objeto Model para pasar datos a la vista
     * @return nombre de la plantilla o redireccion al listado
     */
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        // Buscar el producto por ID; si no existe redirigir al listado
        Producto producto = productoRepo.findById(id).orElse(null);
        if (producto == null) return "redirect:/productos";

        // Pasar el producto encontrado al formulario para pre-llenar los campos
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaRepo.findAll());
        model.addAttribute("proveedores", proveedorRepo.findAll());
        return "productos/formulario";
    }

    /**
     * POST /productos/guardar
     * Recibe los datos del formulario HTML y guarda el producto en la BD.
     * Si el producto tiene ID es una actualizacion; si no tiene ID es insercion.
     * Usa @Valid para validar los campos obligatorios definidos en la entidad.
     * Si hay errores de validacion, vuelve al formulario mostrando los errores.
     *
     * @param producto  objeto producto poblado por Thymeleaf desde el formulario
     * @param resultado resultado de la validacion con los posibles errores
     * @param model     objeto Model para volver al formulario con datos si hay error
     * @param flash     atributos flash para mostrar mensajes despues del redirect
     * @return redireccion al listado o vuelta al formulario si hay errores
     */
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Producto producto,
                          BindingResult resultado,
                          Model model,
                          RedirectAttributes flash) {
        // Si hay errores de validacion, volver al formulario mostrando los mensajes
        if (resultado.hasErrors()) {
            model.addAttribute("categorias", categoriaRepo.findAll());
            model.addAttribute("proveedores", proveedorRepo.findAll());
            return "productos/formulario";
        }
        // Guardar en la base de datos (INSERT si es nuevo, UPDATE si tiene ID)
        productoRepo.save(producto);
        // Mensaje de exito que se muestra despues del redirect
        flash.addFlashAttribute("msg", "Producto guardado correctamente");
        return "redirect:/productos";
    }

    /**
     * GET /productos/eliminar/{id}
     * Elimina un producto de la base de datos por su ID.
     * Redirige al listado con un mensaje de confirmacion.
     *
     * @param id    identificador del producto a eliminar (de la URL)
     * @param flash atributos flash para mostrar mensaje de confirmacion
     * @return redireccion al listado de productos
     */
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes flash) {
        // Eliminar el producto de la base de datos
        productoRepo.deleteById(id);
        flash.addFlashAttribute("msg", "Producto eliminado correctamente");
        return "redirect:/productos";
    }
}
