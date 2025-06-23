package org.velihangozek.vet_clinic_management.dto.request.availabledate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateUpdateRequest {

    @NotNull(message = "AvailableDate id required")
    @Positive(message = "ID value must be positive.")
    private Long id;

    @NotNull(message = "AvailableDate availableDate cannot be empty or null")
    private LocalDate availableDate;

}