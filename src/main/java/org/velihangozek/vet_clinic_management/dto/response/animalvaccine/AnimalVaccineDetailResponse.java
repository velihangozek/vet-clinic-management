package org.velihangozek.vet_clinic_management.dto.response.animalvaccine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalVaccineDetailResponse {

    private Long animalId;
    private String animalName;
    private String species;
    private Long vaccineId;
    private String vaccineName;
    private String vaccineCode;
    private LocalDate protectionFinishDate;

}