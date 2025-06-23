package org.velihangozek.vet_clinic_management.dto.response.animalvaccine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.velihangozek.vet_clinic_management.entities.AnimalVaccineId;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalVaccineResponse {

    private Long animalId;
    private Long vaccineId;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;

}