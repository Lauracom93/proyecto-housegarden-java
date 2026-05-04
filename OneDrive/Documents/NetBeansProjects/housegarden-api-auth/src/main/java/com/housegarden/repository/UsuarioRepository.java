package com.housegarden.repository;

import com.housegarden.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * ============================================================
 * REPOSITORIO: UsuarioRepository
 * ============================================================
 * Interfaz de acceso a datos para la entidad Usuario.
 * Extiende JpaRepository para obtener métodos CRUD automáticos.
 *
 * Método personalizado:
 *   findByUsername() - Busca un usuario por su nombre de usuario.
 *   Spring Data JPA genera la consulta SQL automáticamente:
 *   SELECT * FROM usuarios WHERE username = ?
 *
 * Aprendices: Laura Cómbita - Diana Amaya - Andrés Fontalvo
 * ============================================================
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Busca un usuario por su nombre de usuario.
     * Retorna Optional para manejar el caso en que no exista.
     *
     * @param username nombre de usuario a buscar
     * @return Optional con el usuario encontrado o vacío si no existe
     */
    Optional<Usuario> findByUsername(String username);

    /**
     * Verifica si ya existe un usuario con ese nombre de usuario.
     * Usado en el registro para evitar duplicados.
     *
     * @param username nombre de usuario a verificar
     * @return true si ya existe, false si está disponible
     */
    boolean existsByUsername(String username);
}
