package org.velihangozek.vet_clinic_management.business.abstracts;

import org.springframework.data.domain.Page;
import org.velihangozek.vet_clinic_management.entities.Doctor;

public interface IDoctorService {

    Doctor save(Doctor doctor);

    Doctor get(Long id);

    Doctor update(Doctor doctor);

    boolean delete(Long id);

    Page<Doctor> cursor(int page, int pageSize);

}