package org.velihangozek.vet_clinic_management.dto.request.vaccine;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineSaveRequest {

    @NotBlank(message = "Vaccine name cannot be blank, empty or null")
    private String name;

    @NotBlank(message = "Vaccine code cannot be blank, empty or null")
    private String code;

}