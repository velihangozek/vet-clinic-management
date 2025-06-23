package org.velihangozek.vet_clinic_management.business.concretes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.velihangozek.vet_clinic_management.business.abstracts.IAnimalService;
import org.velihangozek.vet_clinic_management.business.abstracts.IAnimalVaccineService;
import org.velihangozek.vet_clinic_management.core.exception.NotFoundException;
import org.velihangozek.vet_clinic_management.core.utils.Message;
import org.velihangozek.vet_clinic_management.entities.AnimalVaccine;
import org.velihangozek.vet_clinic_management.entities.AnimalVaccineId;
import org.velihangozek.vet_clinic_management.repository.AnimalVaccineRepository;

@Service
public class AnimalVaccineManager implements IAnimalVaccineService {

    private final AnimalVaccineRepository animalVaccineRepository;

    public AnimalVaccineManager(AnimalVaccineRepository animalVaccineRepository) {
        this.animalVaccineRepository = animalVaccineRepository;
    }

    @Override
    public AnimalVaccine save(AnimalVaccine animalVaccine) {
        return this.animalVaccineRepository.save(animalVaccine);
    }

    @Override
    public AnimalVaccine get(AnimalVaccineId id) {
        return this.animalVaccineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.notFound("AnimalVaccine", id)));
    }

    @Override
    public Page<AnimalVaccine> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.animalVaccineRepository.findAll(pageable);
    }

    @Override
    public AnimalVaccine update(AnimalVaccine animalVaccine) {
        this.get(animalVaccine.getId()); // ensuring it already exists (will 404 if not)
        return this.animalVaccineRepository.save(animalVaccine);
    }

    @Override
    public boolean delete(AnimalVaccineId id) {
        AnimalVaccine animalVaccine = this.get(id); // ensuring it already exists (will 404 if not)
        this.animalVaccineRepository.delete(animalVaccine);
        return true;
    }

}