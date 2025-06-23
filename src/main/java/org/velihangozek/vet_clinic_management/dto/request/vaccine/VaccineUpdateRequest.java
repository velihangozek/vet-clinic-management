package org.velihangozek.vet_clinic_management.dto.request.vaccine;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineUpdateRequest {

    @NotNull(message = "Vaccine id required")
    @Positive(message = "ID value must be positive.")
    private Long id;

    @NotBlank(message = "Vaccine name cannot be blank, empty or null")
    private String name;

    @NotBlank(message = "Vaccine code cannot be blank, empty or null")
    private String code;

}