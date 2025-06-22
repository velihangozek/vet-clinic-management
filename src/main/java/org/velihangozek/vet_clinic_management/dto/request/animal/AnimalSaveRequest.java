package org.velihangozek.vet_clinic_management.dto.request.animal;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.velihangozek.vet_clinic_management.entities.Animal;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalSaveRequest {

    @NotNull(message = "Customer id required")
    @Positive(message = "Customer id must be positive")
    private Long customerId;

    @NotBlank(message = "Animal name cannot be blank, empty or null")
    private String name;

    @NotBlank(message = "Animal species cannot be blank, empty or null")
    private String species;

    @NotBlank(message = "Animal breed cannot be blank, empty or null")
    private String breed;

    @NotNull(message = "Animal gender is required")
    @Enumerated(EnumType.STRING)
    private Animal.Gender gender;

    private String color;

    @PastOrPresent
    private LocalDate dateOfBirth;

}