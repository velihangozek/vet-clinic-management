package org.velihangozek.vet_clinic_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.velihangozek.vet_clinic_management.entities.AnimalVaccine;
import org.velihangozek.vet_clinic_management.entities.AnimalVaccineId;

import java.time.LocalDate;
import java.util.List;

public interface AnimalVaccineRepository extends JpaRepository<AnimalVaccine, AnimalVaccineId> {

    @Query("""
                SELECT av FROM AnimalVaccine av
                WHERE av.animal.id = :animalId
                  AND av.vaccine.name = :vaccineName
                  AND av.vaccine.code = :vaccineCode
                  AND av.protectionFinishDate >= :now
            """)
    List<AnimalVaccine> findActiveSameVaccines(
            @Param("animalId") Long animalId,
            @Param("vaccineName") String vaccineName,
            @Param("vaccineCode") String vaccineCode,
            @Param("now") LocalDate now
    );

    @Query("""
                SELECT av FROM AnimalVaccine av
                WHERE av.animal.id = :animalId
            """)
    List<AnimalVaccine> findByAnimalId(@Param("animalId") Long animalId);

    List<AnimalVaccine> findByProtectionFinishDateBetween(LocalDate start, LocalDate end);

}