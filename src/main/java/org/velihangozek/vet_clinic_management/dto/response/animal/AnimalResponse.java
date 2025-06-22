package org.velihangozek.vet_clinic_management.dto.response.animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.velihangozek.vet_clinic_management.entities.Animal;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {

    private Long id;
    private Long customerId;
    private String name;
    private String species;
    private String breed;
    private Animal.Gender gender;
    private String color;
    private LocalDate dateOfBirth;

}