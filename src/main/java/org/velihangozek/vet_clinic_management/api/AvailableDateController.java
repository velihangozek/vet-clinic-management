package org.velihangozek.vet_clinic_management.api;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.velihangozek.vet_clinic_management.business.abstracts.IAvailableDateService;
import org.velihangozek.vet_clinic_management.core.config.modelMapper.IModelMapperService;
import org.velihangozek.vet_clinic_management.core.result.Result;
import org.velihangozek.vet_clinic_management.core.result.ResultData;
import org.velihangozek.vet_clinic_management.core.utils.ResultHelper;
import org.velihangozek.vet_clinic_management.dto.request.availabledate.AvailableDateSaveRequest;
import org.velihangozek.vet_clinic_management.dto.request.availabledate.AvailableDateUpdateRequest;
import org.velihangozek.vet_clinic_management.dto.response.CursorResponse;
import org.velihangozek.vet_clinic_management.dto.response.availabledate.AvailableDateResponse;
import org.velihangozek.vet_clinic_management.entities.AvailableDate;

@RestController
@RequestMapping("/v1/available-dates")
public class AvailableDateController {
    private final IAvailableDateService availableDateService;
    private final IModelMapperService modelMapper;

    public AvailableDateController(
            IAvailableDateService availableDateService,
            IModelMapperService modelMapper) {
        this.availableDateService = availableDateService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@RequestBody @Valid AvailableDateSaveRequest availableDateSaveRequest) {
        AvailableDate availableDate = this.modelMapper.forRequest().map(availableDateSaveRequest, AvailableDate.class);
        AvailableDate savedAvailableDate = this.availableDateService.save(availableDate);
        AvailableDateResponse availableDateResponse = this.modelMapper.forResponse().map(savedAvailableDate, AvailableDateResponse.class);
        return ResultHelper.created(availableDateResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> get(@PathVariable("id") Long id) {
        AvailableDate availableDate = this.availableDateService.get(id);
        AvailableDateResponse availableDateResponse = this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class);
        return ResultHelper.success(availableDateResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AvailableDateResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize
    ) {
        Page<AvailableDate> availableDatePage = this.availableDateService.cursor(page, pageSize);
        Page<AvailableDateResponse> availableDateResponsePage = availableDatePage
                .map(availableDate -> this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class));
        return ResultHelper.cursor(availableDateResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@RequestBody @Valid AvailableDateUpdateRequest availableDateUpdateRequest) {
        AvailableDate availableDate = this.modelMapper.forRequest().map(availableDateUpdateRequest, AvailableDate.class);
        AvailableDate updatedAvailableDate = this.availableDateService.update(availableDate);
        AvailableDateResponse availableDateResponse = this.modelMapper.forResponse().map(updatedAvailableDate, AvailableDateResponse.class);
        return ResultHelper.success(availableDateResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.availableDateService.delete(id);
        return ResultHelper.ok();
    }

}