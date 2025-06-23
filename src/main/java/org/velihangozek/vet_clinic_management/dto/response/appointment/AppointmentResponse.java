package org.velihangozek.vet_clinic_management.dto.response.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {

    private Long id;
    private Long animalId;
    private Long doctorId;
    private LocalDateTime appointmentDateTime;

}