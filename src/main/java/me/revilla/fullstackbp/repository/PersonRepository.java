package me.revilla.fullstackbp.repository;

import me.revilla.fullstackbp.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PersonRepository
 *
 * @author Revilla Pool
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}