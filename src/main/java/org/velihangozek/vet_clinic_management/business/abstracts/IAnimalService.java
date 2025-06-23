package org.velihangozek.vet_clinic_management.business.abstracts;

import org.springframework.data.domain.Page;
import org.velihangozek.vet_clinic_management.entities.Animal;
import org.velihangozek.vet_clinic_management.entities.Doctor;

public interface IAnimalService {

    Animal save(Animal animal);

    Animal get(Long id);

    Page<Animal> cursor(int page, int pageSize);

    Animal update(Animal animal);

    boolean delete(Long id);

}