package com.housegarden.repository;

import com.housegarden.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad Orden.
 * Extiende JpaRepository para obtener automaticamente los metodos CRUD:
 * findAll(), findById(), save(), deleteById(), count(), existsById().
 * Spring Data JPA genera la implementacion en tiempo de ejecucion.
 * No se necesita escribir SQL manualmente.
 *
 * @author Laura Combita - Diana Amaya | Ficha: 3118327
 */
@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {
    // Spring Data JPA genera automaticamente todos los metodos CRUD.
    // Se pueden agregar consultas personalizadas con la convencion de nombres:
    // Ejemplo: List<Orden> findByNombre(String nombre);
}
