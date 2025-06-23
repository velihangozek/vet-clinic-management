package org.velihangozek.vet_clinic_management.dto.request.doctor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DoctorAvailableDateAssignRequest {

    @NotNull(message = "Doctor ID is required")
    @Positive(message = "Doctor ID must be positive")
    private Long doctorId;

    @NotNull(message = "Available date is required")
    private LocalDate availableDate;

}