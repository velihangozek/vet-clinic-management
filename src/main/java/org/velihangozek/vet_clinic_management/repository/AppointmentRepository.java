package org.velihangozek.vet_clinic_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.velihangozek.vet_clinic_management.entities.Animal;
import org.velihangozek.vet_clinic_management.entities.Appointment;
import org.velihangozek.vet_clinic_management.entities.Doctor;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctorAndDateTime(Doctor doctor, LocalDateTime dateTime);

    List<Appointment> findByDoctorAndDateTimeBetween(Doctor doctor, LocalDateTime start, LocalDateTime end);

    List<Appointment> findByAnimalAndDateTimeBetween(Animal animal, LocalDateTime start, LocalDateTime end);

}