package org.velihangozek.vet_clinic_management.business.concretes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.velihangozek.vet_clinic_management.business.abstracts.IAnimalVaccineService;
import org.velihangozek.vet_clinic_management.core.exception.NotFoundException;
import org.velihangozek.vet_clinic_management.core.exception.VaccineConflictException;
import org.velihangozek.vet_clinic_management.core.utils.Message;
import org.velihangozek.vet_clinic_management.entities.AnimalVaccine;
import org.velihangozek.vet_clinic_management.entities.AnimalVaccineId;
import org.velihangozek.vet_clinic_management.repository.AnimalVaccineRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class AnimalVaccineManager implements IAnimalVaccineService {

    private final AnimalVaccineRepository animalVaccineRepository;

    public AnimalVaccineManager(AnimalVaccineRepository animalVaccineRepository) {
        this.animalVaccineRepository = animalVaccineRepository;
    }

    @Override
    public AnimalVaccine save(AnimalVaccine animalVaccine) {

        Long animalId = animalVaccine.getAnimal().getId();
        String vaccineName = animalVaccine.getVaccine().getName();
        String vaccineCode = animalVaccine.getVaccine().getCode();
        LocalDate now = LocalDate.now();

        boolean conflictExists = !animalVaccineRepository
                .findActiveSameVaccines(animalId, vaccineName, vaccineCode, now)
                .isEmpty();

        if (conflictExists) {
            throw new VaccineConflictException(
                    String.format("A valid vaccine (%s - %s) already exists for this animal with future protection date.",
                            vaccineName, vaccineCode));
        }

        return this.animalVaccineRepository.save(animalVaccine);

    }

    @Override
    public AnimalVaccine get(AnimalVaccineId id) {
        return this.animalVaccineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.notFound("AnimalVaccine", id)));
    }

    @Override
    public List<AnimalVaccine> getVaccinesByAnimalId(Long animalId) {
        return this.animalVaccineRepository.findByAnimalId(animalId);
    }

    @Override
    public List<AnimalVaccine> getByProtectionFinishDateRange(LocalDate start, LocalDate end) {
        return this.animalVaccineRepository.findByProtectionFinishDateBetween(start, end);
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