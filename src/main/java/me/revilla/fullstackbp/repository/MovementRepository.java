package me.revilla.fullstackbp.repository;

import me.revilla.fullstackbp.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * MovementRepository
 *
 * @author Revilla Pool
 */
@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    List<Movement> findByDateBetweenAndAccount_Customer_CustomerId(LocalDate dateStart, LocalDate dateEnd, Long customerId);

}