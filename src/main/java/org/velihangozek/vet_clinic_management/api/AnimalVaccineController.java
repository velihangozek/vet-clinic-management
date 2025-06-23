package org.velihangozek.vet_clinic_management.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.velihangozek.vet_clinic_management.business.abstracts.IAnimalService;
import org.velihangozek.vet_clinic_management.business.abstracts.IAnimalVaccineService;
import org.velihangozek.vet_clinic_management.business.abstracts.IVaccineService;
import org.velihangozek.vet_clinic_management.core.config.modelMapper.IModelMapperService;
import org.velihangozek.vet_clinic_management.core.result.ResultData;
import org.velihangozek.vet_clinic_management.core.utils.ResultHelper;
import org.velihangozek.vet_clinic_management.dto.request.animalvaccine.AnimalVaccineSaveRequest;
import org.velihangozek.vet_clinic_management.dto.response.animalvaccine.AnimalVaccineResponse;
import org.velihangozek.vet_clinic_management.entities.Animal;
import org.velihangozek.vet_clinic_management.entities.AnimalVaccine;
import org.velihangozek.vet_clinic_management.entities.Vaccine;

@RestController
@RequestMapping("/v1/animals/vaccines")
public class AnimalVaccineController {
    private final IAnimalVaccineService animalVaccineService;
    private final IAnimalService animalService;
    private final IVaccineService vaccineService;
    private final IModelMapperService modelMapper;

    public AnimalVaccineController(
            IAnimalVaccineService animalVaccineService,
            IAnimalService animalService,
            IVaccineService vaccineService,
            IModelMapperService modelMapper) {
        this.animalVaccineService = animalVaccineService;
        this.animalService = animalService;
        this.vaccineService = vaccineService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalVaccineResponse> save(
            @RequestBody @Valid AnimalVaccineSaveRequest animalVaccineSaveRequest) {
        // 1) load existents
        Animal animal = this.animalService.get(animalVaccineSaveRequest.getAnimalId());
        Vaccine vaccine = this.vaccineService.get(animalVaccineSaveRequest.getVaccineId());
        // 2) build join-entity
        AnimalVaccine animalVaccine = new AnimalVaccine(
                animal,
                vaccine,
                animalVaccineSaveRequest.getProtectionStartDate(),
                animalVaccineSaveRequest.getProtectionFinishDate()
        );
        // 3) save & map back
        AnimalVaccine savedAnimalVaccine = this.animalVaccineService.save(animalVaccine);
        AnimalVaccineResponse animalVaccineResponse = this.modelMapper.forResponse().map(savedAnimalVaccine, AnimalVaccineResponse.class);
        return ResultHelper.created(animalVaccineResponse);
    }

}