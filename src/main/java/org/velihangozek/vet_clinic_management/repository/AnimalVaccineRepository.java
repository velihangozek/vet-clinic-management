package org.velihangozek.vet_clinic_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.velihangozek.vet_clinic_management.entities.AnimalVaccine;
import org.velihangozek.vet_clinic_management.entities.AnimalVaccineId;

public interface AnimalVaccineRepository extends JpaRepository<AnimalVaccine, AnimalVaccineId> {

}