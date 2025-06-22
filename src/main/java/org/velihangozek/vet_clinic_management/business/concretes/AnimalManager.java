package org.velihangozek.vet_clinic_management.business.concretes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.velihangozek.vet_clinic_management.business.abstracts.IAnimalService;
import org.velihangozek.vet_clinic_management.core.exception.NotFoundException;
import org.velihangozek.vet_clinic_management.core.utils.Message;
import org.velihangozek.vet_clinic_management.entities.Animal;
import org.velihangozek.vet_clinic_management.repository.AnimalRepository;

@Service
public class AnimalManager implements IAnimalService {
    private final AnimalRepository animalRepository;

    public AnimalManager(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public Animal save(Animal animal) {
        return this.animalRepository.save(animal);
    }

    @Override
    public Animal get(Long id) {
        return this.animalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.notFound("Animal", id)));
    }

    @Override
    public Page<Animal> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.animalRepository.findAll(pageable);
    }

    @Override
    public Animal update(Animal animal) {
        this.get(animal.getId()); // Check if animal exists
        return this.animalRepository.save(animal);
    }

    @Override
    public boolean delete(Long id) {
        Animal animal = this.get(id); // Check if animal exists
        this.animalRepository.delete(animal);
        return true;
    }

}