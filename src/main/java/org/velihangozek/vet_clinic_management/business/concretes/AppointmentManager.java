package org.velihangozek.vet_clinic_management.business.concretes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.velihangozek.vet_clinic_management.business.abstracts.IAppointmentService;
import org.velihangozek.vet_clinic_management.core.exception.AppointmentConflictException;
import org.velihangozek.vet_clinic_management.core.exception.NotFoundException;
import org.velihangozek.vet_clinic_management.core.utils.Message;
import org.velihangozek.vet_clinic_management.entities.Animal;
import org.velihangozek.vet_clinic_management.entities.Appointment;
import org.velihangozek.vet_clinic_management.entities.Doctor;
import org.velihangozek.vet_clinic_management.repository.AnimalRepository;
import org.velihangozek.vet_clinic_management.repository.AppointmentRepository;
import org.velihangozek.vet_clinic_management.repository.DoctorRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentManager implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final AnimalRepository animalRepository;

    public AppointmentManager(
            AppointmentRepository appointmentRepository,
            DoctorRepository doctorRepository,
            AnimalRepository animalRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public Appointment save(Appointment appointment) {
        Doctor doctor = doctorRepository.findById(appointment.getDoctor().getId())
                .orElseThrow(() -> new NotFoundException(Message.notFound("Doctor", appointment.getDoctor().getId())));
        Animal animal = animalRepository.findById(appointment.getAnimal().getId())
                .orElseThrow(() -> new NotFoundException(Message.notFound("Animal", appointment.getAnimal().getId())));

        // Check if the doctor is available that day
        boolean isAvailable = doctor.getAvailableDates().stream()
                .anyMatch(availableDate -> availableDate.getAvailableDate().isEqual(appointment.getDateTime().toLocalDate()));

        if (!isAvailable) {
            throw new AppointmentConflictException("Doctor is not available on this date.");
        }

        // Check for time conflict (hourly)
        if (appointmentRepository.existsByDoctorAndDateTime(doctor, appointment.getDateTime())) {
            throw new AppointmentConflictException("Doctor already has an appointment at this time.");
        }

        appointment.setDoctor(doctor);
        appointment.setAnimal(animal);

        // For bidirectional consistency, thanks to @PrePersist, no need to manually add to doctor's/animal's appointment list anymore.

        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctorAndDateRange(Long doctorId, LocalDateTime start, LocalDateTime end) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException(Message.notFound("Doctor", doctorId)));
        return appointmentRepository.findByDoctorAndDateTimeBetween(doctor, start, end);
    }

    @Override
    public List<Appointment> getAppointmentsByAnimalAndDateRange(Long animalId, LocalDateTime start, LocalDateTime end) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new NotFoundException(Message.notFound("Animal", animalId)));
        return appointmentRepository.findByAnimalAndDateTimeBetween(animal, start, end);
    }

    @Override
    public Appointment get(Long id) {
        return this.appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.notFound("Appointment", id)));
    }

    @Override
    public Page<Appointment> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.appointmentRepository.findAll(pageable);
    }

    @Override
    public Appointment update(Long id, Appointment updatedAppointment) {

        Appointment existingAppointment = this.get(id); // Check if appointment exists

        Doctor doctor = doctorRepository.findById(updatedAppointment.getDoctor().getId())
                .orElseThrow(() -> new NotFoundException(Message.notFound("Doctor", updatedAppointment.getDoctor().getId())));
        Animal animal = animalRepository.findById(updatedAppointment.getAnimal().getId())
                .orElseThrow(() -> new NotFoundException(Message.notFound("Animal", updatedAppointment.getAnimal().getId())));

        boolean isAvailable = doctor.getAvailableDates().stream()
                .anyMatch(availableDate -> availableDate.getAvailableDate().isEqual(updatedAppointment.getDateTime().toLocalDate()));

        if (!isAvailable) {
            throw new AppointmentConflictException("Doctor is not available on this date.");
        }

        boolean isConflict = appointmentRepository.existsByDoctorAndDateTime(doctor, updatedAppointment.getDateTime())
                && !existingAppointment.getDateTime().equals(updatedAppointment.getDateTime());

        if (isConflict) {
            throw new AppointmentConflictException("Doctor already has an appointment at this time.");
        }

        // Update core fields
        existingAppointment.setDoctor(doctor);
        existingAppointment.setAnimal(animal);
        existingAppointment.setDateTime(updatedAppointment.getDateTime());

        // For bidirectional consistency, thanks to @PreUpdate, it'll re-add to the new doctor/animal

        return appointmentRepository.save(existingAppointment);
    }

    @Override
    public boolean delete(Long id) {
        Appointment appointment = this.get(id); // Check existence
        this.appointmentRepository.delete(appointment); // preRemove() auto-handles references
        return true;
    }
}