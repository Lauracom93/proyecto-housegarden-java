package com.housegarden.service;

import com.housegarden.model.Producto;
import com.housegarden.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Clase de servicio para la logica de negocio de Producto.
 * La capa de servicio separa la logica de negocio del controlador.
 * @Service indica a Spring que esta clase es un componente de servicio.
 *
 * @author Laura Combita - Diana Amaya | Ficha: 3118327
 */
@Service
public class ProductoService {

    /** Repositorio inyectado por Spring para acceso a la base de datos */
    @Autowired
    private ProductoRepository repository;

    /**
     * Obtiene la lista completa de todos los registros de Producto.
     * @return lista de todos los objetos Producto en la base de datos
     */
    public List<Producto> listarTodos() {
        return repository.findAll();
    }

    /**
     * Busca un registro de Producto por su identificador unico.
     * @param id identificador del registro a buscar
     * @return objeto Producto encontrado, o null si no existe
     */
    public Producto buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Guarda un nuevo registro o actualiza uno existente en la base de datos.
     * Si el objeto tiene ID, realiza una actualizacion (UPDATE).
     * Si el objeto no tiene ID, realiza una insercion (INSERT).
     * @param entidad objeto a guardar o actualizar
     */
    public void guardar(Producto entidad) {
        repository.save(entidad);
    }

    /**
     * Elimina un registro de la base de datos por su identificador.
     * @param id identificador del registro a eliminar
     */
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
