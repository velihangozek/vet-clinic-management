package org.velihangozek.vet_clinic_management.dto.request.availabledate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateSaveRequest {

    @NotNull(message = "AvailableDate availableDate cannot be empty or null")
    private LocalDate availableDate;

}