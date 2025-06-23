package org.velihangozek.vet_clinic_management.business.abstracts;

import org.springframework.data.domain.Page;
import org.velihangozek.vet_clinic_management.entities.Doctor;

import java.time.LocalDate;

public interface IDoctorService {

    Doctor save(Doctor doctor);

    Doctor get(Long id);

    Page<Doctor> cursor(int page, int pageSize);

    Doctor update(Doctor doctor);

    boolean delete(Long id);

    Doctor assignAvailableDate(Long doctorId, LocalDate availableDate);

}