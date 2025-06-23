package org.velihangozek.vet_clinic_management.business.concretes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.velihangozek.vet_clinic_management.business.abstracts.IDoctorService;
import org.velihangozek.vet_clinic_management.core.exception.NotFoundException;
import org.velihangozek.vet_clinic_management.core.utils.Message;
import org.velihangozek.vet_clinic_management.entities.AvailableDate;
import org.velihangozek.vet_clinic_management.entities.Doctor;
import org.velihangozek.vet_clinic_management.repository.AvailableDateRepository;
import org.velihangozek.vet_clinic_management.repository.DoctorRepository;

import java.time.LocalDate;

@Service
public class DoctorManager implements IDoctorService {
    private final DoctorRepository doctorRepository;
    private final AvailableDateRepository availableDateRepository;

    public DoctorManager(
            DoctorRepository doctorRepository,
            AvailableDateRepository availableDateRepository) {
        this.doctorRepository = doctorRepository;
        this.availableDateRepository = availableDateRepository;
    }

    @Override
    public Doctor save(Doctor doctor) {
        return this.doctorRepository.save(doctor);
    }

    @Override
    public Doctor get(Long id) {
        return this.doctorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.notFound("Doctor", id)));
    }

    @Override
    public Page<Doctor> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.doctorRepository.findAll(pageable);
    }

    @Override
    public Doctor update(Doctor doctor) {
        this.get(doctor.getId()); // Check if doctor exists
        return this.doctorRepository.save(doctor);
    }

    @Override
    public boolean delete(Long id) {
        Doctor doctor = this.get(id); // Check if doctor exists
        this.doctorRepository.delete(doctor);
        return true;
    }

    @Override
    public Doctor assignAvailableDate(Long doctorId, LocalDate availableDate) {
        Doctor doctor = this.get(doctorId);
        AvailableDate date = this.availableDateRepository.findByAvailableDate(availableDate)
                .orElseGet(() -> this.availableDateRepository.save(new AvailableDate(availableDate)));
        doctor.getAvailableDates().add(date);
        date.getDoctors().add(doctor); // Bi-directional
        return this.doctorRepository.save(doctor);
    }

}