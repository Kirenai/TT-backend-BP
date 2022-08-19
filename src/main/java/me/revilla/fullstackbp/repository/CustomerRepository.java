package me.revilla.fullstackbp.repository;

import me.revilla.fullstackbp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CustomerRepository
 *
 * @author Revilla Pool
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}