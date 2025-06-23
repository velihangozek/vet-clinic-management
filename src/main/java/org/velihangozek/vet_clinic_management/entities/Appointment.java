package org.velihangozek.vet_clinic_management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "appointment_animal_id", nullable = false)
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "appointment_doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name = "appointment_date", nullable = false)
    private LocalDateTime dateTime;

    @PrePersist
    @PreUpdate
    private void ensureBidirectionalAssociation() {
        if (doctor != null && !doctor.getAppointments().contains(this)) {
            doctor.getAppointments().add(this);
        }
        if (animal != null && !animal.getAppointments().contains(this)) {
            animal.getAppointments().add(this);
        }
    }

}