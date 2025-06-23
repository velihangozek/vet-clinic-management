package org.velihangozek.vet_clinic_management.api;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.velihangozek.vet_clinic_management.business.abstracts.IVaccineService;
import org.velihangozek.vet_clinic_management.core.config.modelMapper.IModelMapperService;
import org.velihangozek.vet_clinic_management.core.result.Result;
import org.velihangozek.vet_clinic_management.core.result.ResultData;
import org.velihangozek.vet_clinic_management.core.utils.ResultHelper;
import org.velihangozek.vet_clinic_management.dto.request.vaccine.VaccineSaveRequest;
import org.velihangozek.vet_clinic_management.dto.request.vaccine.VaccineUpdateRequest;
import org.velihangozek.vet_clinic_management.dto.response.CursorResponse;
import org.velihangozek.vet_clinic_management.dto.response.vaccine.VaccineResponse;
import org.velihangozek.vet_clinic_management.entities.Vaccine;

@RestController
@RequestMapping("/v1/vaccines")
public class VaccineController {
    private final IVaccineService vaccineService;
    private final IModelMapperService modelMapper;

    public VaccineController(
            IVaccineService vaccineService,
            IModelMapperService modelMapper) {
        this.vaccineService = vaccineService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@RequestBody @Valid VaccineSaveRequest vaccineSaveRequest) {
        Vaccine vaccine = this.modelMapper.forRequest().map(vaccineSaveRequest, Vaccine.class);
        Vaccine savedVaccine = this.vaccineService.save(vaccine);
        VaccineResponse vaccineResponse = this.modelMapper.forResponse().map(savedVaccine, VaccineResponse.class);
        return ResultHelper.created(vaccineResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> get(@PathVariable("id") Long id) {
        Vaccine vaccine = this.vaccineService.get(id);
        VaccineResponse vaccineResponse = this.modelMapper.forResponse().map(vaccine, VaccineResponse.class);
        return ResultHelper.success(vaccineResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<VaccineResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize
    ) {
        Page<Vaccine> vaccinePage = this.vaccineService.cursor(page, pageSize);
        Page<VaccineResponse> vaccineResponsePage = vaccinePage
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class));
        return ResultHelper.cursor(vaccineResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@RequestBody @Valid VaccineUpdateRequest vaccineUpdateRequest) {
        Vaccine vaccine = this.modelMapper.forRequest().map(vaccineUpdateRequest, Vaccine.class);
        Vaccine updatedVaccine = this.vaccineService.update(vaccine);
        VaccineResponse vaccineResponse = this.modelMapper.forResponse().map(updatedVaccine, VaccineResponse.class);
        return ResultHelper.success(vaccineResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.vaccineService.delete(id);
        return ResultHelper.ok();
    }

}