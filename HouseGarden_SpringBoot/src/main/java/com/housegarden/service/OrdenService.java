package com.housegarden.service;

import com.housegarden.model.Orden;
import com.housegarden.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Clase de servicio para la logica de negocio de Orden.
 * La capa de servicio separa la logica de negocio del controlador.
 * @Service indica a Spring que esta clase es un componente de servicio.
 *
 * @author Laura Combita - Diana Amaya | Ficha: 3118327
 */
@Service
public class OrdenService {

    /** Repositorio inyectado por Spring para acceso a la base de datos */
    @Autowired
    private OrdenRepository repository;

    /**
     * Obtiene la lista completa de todos los registros de Orden.
     * @return lista de todos los objetos Orden en la base de datos
     */
    public List<Orden> listarTodos() {
        return repository.findAll();
    }

    /**
     * Busca un registro de Orden por su identificador unico.
     * @param id identificador del registro a buscar
     * @return objeto Orden encontrado, o null si no existe
     */
    public Orden buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Guarda un nuevo registro o actualiza uno existente en la base de datos.
     * Si el objeto tiene ID, realiza una actualizacion (UPDATE).
     * Si el objeto no tiene ID, realiza una insercion (INSERT).
     * @param entidad objeto a guardar o actualizar
     */
    public void guardar(Orden entidad) {
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
