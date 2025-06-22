package org.velihangozek.vet_clinic_management.dto.request.doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSaveRequest {

    @NotBlank(message = "Doctor name cannot be blank, empty or null")
    private String name;

    @NotBlank(message = "Doctor phone cannot be blank, empty or null")
    private String phone;

    @NotBlank(message = "Doctor email cannot be blank, empty or null")
    @Email(message = "Please provide a valid email address")
    private String mail;

    private String address;

    private String city;

}