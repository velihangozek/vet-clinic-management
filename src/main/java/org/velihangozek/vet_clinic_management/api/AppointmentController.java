package org.velihangozek.vet_clinic_management.api;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.velihangozek.vet_clinic_management.business.abstracts.IAppointmentService;
import org.velihangozek.vet_clinic_management.core.config.modelMapper.IModelMapperService;
import org.velihangozek.vet_clinic_management.core.result.Result;
import org.velihangozek.vet_clinic_management.core.result.ResultData;
import org.velihangozek.vet_clinic_management.core.utils.ResultHelper;
import org.velihangozek.vet_clinic_management.dto.request.appointment.AppointmentSaveRequest;
import org.velihangozek.vet_clinic_management.dto.response.CursorResponse;
import org.velihangozek.vet_clinic_management.dto.response.appointment.AppointmentResponse;
import org.velihangozek.vet_clinic_management.entities.Appointment;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {

    private final IAppointmentService appointmentService;
    private final IModelMapperService modelMapper;

    public AppointmentController(
            IAppointmentService appointmentService,
            IModelMapperService modelMapper) {
        this.appointmentService = appointmentService;
        this.modelMapper = modelMapper;
    }

    // CREATE
    @PostMapping
    public ResultData<AppointmentResponse> save(@RequestBody @Valid AppointmentSaveRequest appointmentSaveRequest) {
        Appointment appointment = this.modelMapper.forRequest().map(appointmentSaveRequest, Appointment.class);
        Appointment savedAppointment = this.appointmentService.save(appointment);
        AppointmentResponse appointmentResponse = this.modelMapper.forResponse().map(savedAppointment, AppointmentResponse.class);
        return ResultHelper.created(appointmentResponse);
    }

    // READ by ID
    @GetMapping("/{id}")
    public ResultData<AppointmentResponse> getById(@PathVariable Long id) {
        Appointment appointment = appointmentService.get(id);
        AppointmentResponse response = modelMapper.forResponse().map(appointment, AppointmentResponse.class);
        return ResultHelper.success(response);
    }

    // READ by Doctor + Date Range
    @GetMapping("/by-doctor")
    public ResultData<List<AppointmentResponse>> getByDoctorAndDateRange(
            @RequestParam Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        List<Appointment> appointmentList = this.appointmentService.getAppointmentsByDoctorAndDateRange(doctorId, startDate, endDate);
        List<AppointmentResponse> appointmentResponseList = appointmentList.stream()
                .map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .toList();
        return ResultHelper.success(appointmentResponseList);
    }

    // READ by Animal + Date Range
    @GetMapping("/by-animal")
    public ResultData<List<AppointmentResponse>> getByAnimalAndDateRange(
            @RequestParam Long animalId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        List<Appointment> appointmentList = this.appointmentService.getAppointmentsByAnimalAndDateRange(animalId, startDate, endDate);
        List<AppointmentResponse> appointmentResponseList = appointmentList.stream()
                .map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .toList();
        return ResultHelper.success(appointmentResponseList);
    }

    // PAGINATED (Cursor) Get All
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize
    ) {
        Page<Appointment> appointmentPage = this.appointmentService.cursor(page, pageSize);
        Page<AppointmentResponse> appointmentResponsePage = appointmentPage.map(
                appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class)
        );
        return ResultHelper.cursor(appointmentResponsePage);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResultData<AppointmentResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid AppointmentSaveRequest appointmentSaveRequest) {
        Appointment updated = this.modelMapper.forRequest().map(appointmentSaveRequest, Appointment.class);
        Appointment saved = this.appointmentService.update(id, updated);
        AppointmentResponse response = modelMapper.forResponse().map(saved, AppointmentResponse.class);
        return ResultHelper.success(response);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean result = this.appointmentService.delete(id);
        return result ? ResultHelper.ok() : ResultHelper.notFoundError();
    }

}