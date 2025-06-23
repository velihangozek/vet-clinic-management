package org.velihangozek.vet_clinic_management.business.abstracts;

import org.springframework.data.domain.Page;
import org.velihangozek.vet_clinic_management.entities.Animal;
import org.velihangozek.vet_clinic_management.entities.AnimalVaccine;
import org.velihangozek.vet_clinic_management.entities.AnimalVaccineId;

import java.time.LocalDate;
import java.util.List;

public interface IAnimalVaccineService {

    AnimalVaccine save(AnimalVaccine animalVaccine);

    AnimalVaccine get(AnimalVaccineId id);

    List<AnimalVaccine> getVaccinesByAnimalId(Long animalId);

    List<AnimalVaccine> getByProtectionFinishDateRange(LocalDate start, LocalDate end);

    Page<AnimalVaccine> cursor(int page, int pageSize);

    AnimalVaccine update(AnimalVaccine animalVaccine);

    boolean delete(AnimalVaccineId id);

}