package org.velihangozek.vet_clinic_management.business.abstracts;

import org.springframework.data.domain.Page;
import org.velihangozek.vet_clinic_management.entities.Animal;
import org.velihangozek.vet_clinic_management.entities.Vaccine;

public interface IVaccineService {

    Vaccine save(Vaccine vaccine);

    Vaccine get(Long id);

    Vaccine update(Vaccine vaccine);

    boolean delete(Long id);

    Page<Vaccine> cursor(int page, int pageSize);

}