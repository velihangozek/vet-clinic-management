package org.velihangozek.vet_clinic_management.business.concretes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.velihangozek.vet_clinic_management.business.abstracts.IAvailableDateService;
import org.velihangozek.vet_clinic_management.core.exception.NotFoundException;
import org.velihangozek.vet_clinic_management.core.utils.Message;
import org.velihangozek.vet_clinic_management.entities.AvailableDate;
import org.velihangozek.vet_clinic_management.repository.AvailableDateRepository;

@Service
public class AvailableDateManager implements IAvailableDateService {
    private AvailableDateRepository availableDateRepository;

    public AvailableDateManager(AvailableDateRepository availableDateRepository) {
        this.availableDateRepository = availableDateRepository;
    }

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        return this.availableDateRepository.save(availableDate);
    }

    @Override
    public AvailableDate get(Long id) {
        return this.availableDateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.notFound("AvailableDate", id)));
    }

    @Override
    public Page<AvailableDate> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.availableDateRepository.findAll(pageable);
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        this.get(availableDate.getId()); // Check if availableDate exists
        return this.availableDateRepository.save(availableDate);
    }

    @Override
    public boolean delete(Long id) {
        AvailableDate availableDate = this.get(id); // Check if availableDate exists

        // Clear relationships from doctors
        if (!availableDate.getDoctors().isEmpty()) {
            availableDate.getDoctors().forEach(doctor -> doctor.getAvailableDates().remove(availableDate));
            availableDate.getDoctors().clear(); // Clear owning side to avoid further cascade issues
        }

        this.availableDateRepository.save(availableDate); // Save the dissociation
        this.availableDateRepository.delete(availableDate); // Now safely delete

        return true;
    }

}