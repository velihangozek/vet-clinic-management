package org.velihangozek.vet_clinic_management.dto.request.appointment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSaveRequest {

    @NotNull
    @Positive
    private Long animalId;

    @NotNull
    @Positive
    private Long doctorId;

    @NotNull
    private LocalDateTime appointmentDateTime;

}