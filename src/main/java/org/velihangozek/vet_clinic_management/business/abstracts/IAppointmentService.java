package org.velihangozek.vet_clinic_management.business.abstracts;

import org.springframework.data.domain.Page;
import org.velihangozek.vet_clinic_management.entities.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {

    Appointment save(Appointment appointment);

    Appointment get(Long id);

    List<Appointment> getAppointmentsByDoctorAndDateRange(Long doctorId, LocalDateTime start, LocalDateTime end);

    List<Appointment> getAppointmentsByAnimalAndDateRange(Long animalId, LocalDateTime start, LocalDateTime end);

    Page<Appointment> cursor(int page, int pageSize);

    Appointment update(Long id, Appointment appointment);

    boolean delete(Long id);

}