package org.velihangozek.vet_clinic_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.velihangozek.vet_clinic_management.entities.Animal;
import org.velihangozek.vet_clinic_management.entities.Customer;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByNameContainingIgnoreCase(String name);

    List<Animal> findByCustomerId(Long customerId);

}