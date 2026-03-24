package com.housegarden.service;

import com.housegarden.model.Proveedor;
import com.housegarden.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Clase de servicio para la logica de negocio de Proveedor.
 * La capa de servicio separa la logica de negocio del controlador.
 * @Service indica a Spring que esta clase es un componente de servicio.
 *
 * @author Laura Combita - Diana Amaya | Ficha: 3118327
 */
@Service
public class ProveedorService {

    /** Repositorio inyectado por Spring para acceso a la base de datos */
    @Autowired
    private ProveedorRepository repository;

    /**
     * Obtiene la lista completa de todos los registros de Proveedor.
     * @return lista de todos los objetos Proveedor en la base de datos
     */
    public List<Proveedor> listarTodos() {
        return repository.findAll();
    }

    /**
     * Busca un registro de Proveedor por su identificador unico.
     * @param id identificador del registro a buscar
     * @return objeto Proveedor encontrado, o null si no existe
     */
    public Proveedor buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Guarda un nuevo registro o actualiza uno existente en la base de datos.
     * Si el objeto tiene ID, realiza una actualizacion (UPDATE).
     * Si el objeto no tiene ID, realiza una insercion (INSERT).
     * @param entidad objeto a guardar o actualizar
     */
    public void guardar(Proveedor entidad) {
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
