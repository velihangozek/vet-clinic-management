package org.velihangozek.vet_clinic_management.business.abstracts;

import org.springframework.data.domain.Page;
import org.velihangozek.vet_clinic_management.entities.Animal;
import org.velihangozek.vet_clinic_management.entities.AnimalVaccine;
import org.velihangozek.vet_clinic_management.entities.AnimalVaccineId;

public interface IAnimalVaccineService {

    AnimalVaccine save(AnimalVaccine animalVaccine);

    AnimalVaccine get(AnimalVaccineId id);

    AnimalVaccine update(AnimalVaccine animalVaccine);

    boolean delete(AnimalVaccineId id);

    Page<AnimalVaccine> cursor(int page, int pageSize);

}