package org.velihangozek.vet_clinic_management.business.abstracts;

import org.springframework.data.domain.Page;
import org.velihangozek.vet_clinic_management.entities.AvailableDate;

public interface IAvailableDateService {

    AvailableDate save(AvailableDate availableDate);

    AvailableDate get(Long id);

    AvailableDate update(AvailableDate availableDate);

    boolean delete(Long id);

    Page<AvailableDate> cursor(int page, int pageSize);

}