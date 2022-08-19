package me.revilla.fullstackbp.repository;

import me.revilla.fullstackbp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * AccountRepository
 *
 * @author Revilla Pool
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}