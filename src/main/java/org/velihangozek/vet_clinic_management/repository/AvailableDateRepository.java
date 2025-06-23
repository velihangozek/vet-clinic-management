package org.velihangozek.vet_clinic_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.velihangozek.vet_clinic_management.entities.AvailableDate;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {

    Optional<AvailableDate> findByAvailableDate(LocalDate availableDate);

}