package org.velihangozek.vet_clinic_management.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AnimalVaccineId implements Serializable {

    @Column(name = "animal2vaccine_animal_id")
    private Long animalId;

    @Column(name = "animal2vaccine_vaccine_id")
    private Long vaccineId;

    // standard equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnimalVaccineId that)) return false;
        return Objects.equals(animalId, that.animalId)
                && Objects.equals(vaccineId, that.vaccineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalId, vaccineId);
    }

}