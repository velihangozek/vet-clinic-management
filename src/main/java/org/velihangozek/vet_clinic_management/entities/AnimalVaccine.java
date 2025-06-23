package org.velihangozek.vet_clinic_management.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "animals2vaccines")
public class AnimalVaccine {

    @EmbeddedId
    private AnimalVaccineId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("animalId")
    @NotNull
    @JoinColumn(name = "animal2vaccine_animal_id")
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("vaccineId")
    @NotNull
    @JoinColumn(name = "animal2vaccine_vaccine_id")
    private Vaccine vaccine;

    @NotNull
    @PastOrPresent
    @Column(name = "animal2vaccine_vaccine_protection_start_date", nullable = false)
    private LocalDate protectionStartDate;

    @NotNull
    @Column(name = "animal2vaccine_vaccine_protection_finish_date", nullable = false)
    private LocalDate protectionFinishDate;

    public AnimalVaccine(Animal animal, Vaccine vaccine,
                         LocalDate start, LocalDate finish) {
        this.animal = animal;
        this.vaccine = vaccine;
        this.id = new AnimalVaccineId(animal.getId(), vaccine.getId());
        this.protectionStartDate = start;
        this.protectionFinishDate = finish;
    }

}