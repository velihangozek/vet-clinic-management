package org.velihangozek.vet_clinic_management.business.concretes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.velihangozek.vet_clinic_management.business.abstracts.IVaccineService;
import org.velihangozek.vet_clinic_management.core.exception.NotFoundException;
import org.velihangozek.vet_clinic_management.core.utils.Message;
import org.velihangozek.vet_clinic_management.entities.Vaccine;
import org.velihangozek.vet_clinic_management.repository.VaccineRepository;

@Service
public class VaccineManager implements IVaccineService {
    private final VaccineRepository vaccineRepository;

    public VaccineManager(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    @Override
    public Vaccine save(Vaccine vaccine) {
        return this.vaccineRepository.save(vaccine);
    }

    @Override
    public Vaccine get(Long id) {
        return this.vaccineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.notFound("Vaccine", id)));
    }

    @Override
    public Page<Vaccine> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.vaccineRepository.findAll(pageable);
    }

    @Override
    public Vaccine update(Vaccine vaccine) {
        this.get(vaccine.getId()); // Check if vaccine exists
        return this.vaccineRepository.save(vaccine);
    }

    @Override
    public boolean delete(Long id) {
        Vaccine vaccine = this.get(id); // Check if vaccine exists
        this.vaccineRepository.delete(vaccine);
        return true;
    }

}