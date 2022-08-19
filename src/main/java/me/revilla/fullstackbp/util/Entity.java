package me.revilla.fullstackbp.util;

import me.revilla.fullstackbp.exception.entity.EntityNoSuchElementException;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Entity
 *
 * @author Revilla Pool
 */
public class Entity {

    public static <E, ID extends Long, R extends JpaRepository<E, ID>> E getById(
            ID id,
            R repo,
            Class<E> entityClass) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNoSuchElementException(
                        entityClass.getSimpleName()
                                + " not found with id: " + id)
                );
    }

}
