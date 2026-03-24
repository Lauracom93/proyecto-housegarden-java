package com.housegarden.config;

import com.housegarden.model.Categoria;
import com.housegarden.model.Cliente;
import com.housegarden.model.Proveedor;
import com.housegarden.repository.CategoriaRepository;
import com.housegarden.repository.ClienteRepository;
import com.housegarden.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ============================================================
 * CONFIGURACION: WebConfig
 * ============================================================
 * Clase de configuracion Web MVC de Spring.
 * Registra conversores (Converters) que Spring usa automaticamente
 * para transformar los valores de los campos select del formulario
 * HTML (que envian el ID como String) en objetos Java completos.
 *
 * Problema que resuelve:
 *   Cuando el usuario selecciona un cliente en el formulario de ordenes,
 *   el navegador envia el ID como texto (ej: "1").
 *   El objeto Orden necesita un objeto Cliente completo, no solo el ID.
 *   Este conversor busca el Cliente en la BD usando el ID recibido.
 *
 * Aprendices: Laura Combita - Diana Amaya | Ficha: 3118327
 * ============================================================
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /** Repositorio de clientes para buscar por ID en el conversor */
    @Autowired private ClienteRepository clienteRepo;

    /** Repositorio de categorias para buscar por ID en el conversor */
    @Autowired private CategoriaRepository categoriaRepo;

    /** Repositorio de proveedores para buscar por ID en el conversor */
    @Autowired private ProveedorRepository proveedorRepo;

    /**
     * Registra los conversores de tipo String -> Entidad JPA.
     * Spring los invoca automaticamente al procesar los formularios HTML.
     *
     * @param registry registro de formateadores y conversores de Spring MVC
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {

        // Conversor: String (ID del select) -> objeto Cliente completo
        registry.addConverter(new Converter<String, Cliente>() {
            public Cliente convert(String id) {
                // Si el campo esta vacio (opcion "seleccione..."), retornar null
                if (id == null || id.isEmpty()) return null;
                // Buscar el Cliente en MySQL por su ID
                return clienteRepo.findById(Integer.parseInt(id)).orElse(null);
            }
        });

        // Conversor: String (ID del select) -> objeto Categoria completo
        registry.addConverter(new Converter<String, Categoria>() {
            public Categoria convert(String id) {
                if (id == null || id.isEmpty()) return null;
                // Buscar la Categoria en MySQL por su ID
                return categoriaRepo.findById(Integer.parseInt(id)).orElse(null);
            }
        });

        // Conversor: String (ID del select) -> objeto Proveedor completo
        registry.addConverter(new Converter<String, Proveedor>() {
            public Proveedor convert(String id) {
                if (id == null || id.isEmpty()) return null;
                // Buscar el Proveedor en MySQL por su ID
                return proveedorRepo.findById(Integer.parseInt(id)).orElse(null);
            }
        });
    }
}
