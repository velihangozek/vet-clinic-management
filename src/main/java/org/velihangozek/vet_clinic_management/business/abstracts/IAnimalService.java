package org.velihangozek.vet_clinic_management.business.abstracts;

import org.springframework.data.domain.Page;
import org.velihangozek.vet_clinic_management.entities.Animal;

import java.util.List;

public interface IAnimalService {

    Animal save(Animal animal);

    Animal get(Long id);

    List<Animal> searchByName(String name);

    List<Animal> getAnimalsByCustomerId(Long customerId);

    Page<Animal> cursor(int page, int pageSize);

    Animal update(Animal animal);

    boolean delete(Long id);

}