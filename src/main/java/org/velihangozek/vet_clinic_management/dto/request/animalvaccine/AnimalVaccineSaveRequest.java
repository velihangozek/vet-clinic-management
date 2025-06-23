package org.velihangozek.vet_clinic_management.dto.request.animalvaccine;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalVaccineSaveRequest {

    @NotNull(message = "Animal id required")
    @Positive(message = "Animal id must be positive")
    private Long animalId;

    @NotNull(message = "Vaccine id required")
    @Positive(message = "Vaccine id must be positive")
    private Long vaccineId;

    @NotNull(message = "Vaccine Protection Start Date required")
    @PastOrPresent(message = "Protection start cannot be in the future")
    private LocalDate protectionStartDate;

    @NotNull(message = "Vaccine Protection Finish Date required")
    private LocalDate protectionFinishDate;

    /**
     * This method will be invoked by the validator: must return true,
     * or the whole DTO will be considered invalid.
     */
    @AssertTrue(message = "protectionFinishDate must be after protectionStartDate")
    public boolean isDateRangeValid() {
        // if either is null, other @NotNull annotations will catch it
        if (protectionStartDate == null || protectionFinishDate == null) {
            // let @NotNull on each field produce its own error
            return true;
        }
        return protectionFinishDate.isAfter(protectionStartDate);
    }

}