package org.velihangozek.vet_clinic_management.api;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.velihangozek.vet_clinic_management.business.abstracts.ICustomerService;
import org.velihangozek.vet_clinic_management.business.abstracts.IDoctorService;
import org.velihangozek.vet_clinic_management.core.config.modelMapper.IModelMapperService;
import org.velihangozek.vet_clinic_management.core.result.Result;
import org.velihangozek.vet_clinic_management.core.result.ResultData;
import org.velihangozek.vet_clinic_management.core.utils.ResultHelper;
import org.velihangozek.vet_clinic_management.dto.request.customer.CustomerSaveRequest;
import org.velihangozek.vet_clinic_management.dto.request.customer.CustomerUpdateRequest;
import org.velihangozek.vet_clinic_management.dto.request.doctor.DoctorSaveRequest;
import org.velihangozek.vet_clinic_management.dto.request.doctor.DoctorUpdateRequest;
import org.velihangozek.vet_clinic_management.dto.response.CursorResponse;
import org.velihangozek.vet_clinic_management.dto.response.customer.CustomerResponse;
import org.velihangozek.vet_clinic_management.dto.response.doctor.DoctorResponse;
import org.velihangozek.vet_clinic_management.entities.Customer;
import org.velihangozek.vet_clinic_management.entities.Doctor;

@RestController
@RequestMapping("/v1/doctors")
public class DoctorController {
    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    public DoctorController(IDoctorService doctorService, IModelMapperService modelMapper) {
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> save(@RequestBody @Valid DoctorSaveRequest doctorSaveRequest) {
        Doctor doctor = this.modelMapper.forRequest().map(doctorSaveRequest, Doctor.class);
        Doctor savedDoctor = this.doctorService.save(doctor);
        DoctorResponse doctorResponse = this.modelMapper.forResponse().map(savedDoctor, DoctorResponse.class);
        return ResultHelper.created(doctorResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> get(@PathVariable("id") Long id) {
        Doctor doctor = this.doctorService.get(id);
        DoctorResponse doctorResponse = this.modelMapper.forResponse().map(doctor, DoctorResponse.class);
        return ResultHelper.success(doctorResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<DoctorResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize
    ) {
        Page<Doctor> doctorPage = this.doctorService.cursor(page, pageSize);
        Page<DoctorResponse> doctorResponsePage = doctorPage
                .map(doctor -> this.modelMapper.forResponse().map(doctor, DoctorResponse.class));
        return ResultHelper.cursor(doctorResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> update(@RequestBody @Valid DoctorUpdateRequest doctorUpdateRequest) {
        Doctor doctor = this.modelMapper.forRequest().map(doctorUpdateRequest, Doctor.class);
        Doctor updatedDoctor = this.doctorService.update(doctor);
        DoctorResponse doctorResponse = this.modelMapper.forResponse().map(updatedDoctor, DoctorResponse.class);
        return ResultHelper.success(doctorResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.doctorService.delete(id);
        return ResultHelper.ok();
    }
}