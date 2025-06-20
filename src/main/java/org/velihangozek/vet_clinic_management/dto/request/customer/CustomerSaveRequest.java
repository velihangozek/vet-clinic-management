package org.velihangozek.vet_clinic_management.dto.request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {

    @NotBlank(message = "Customer name cannot be blank, empty or null")
    private String name;

    @NotBlank(message = "Customer phone cannot be blank, empty or null")
    private String phone;

    @NotBlank(message = "Customer email cannot be blank, empty or null")
    @Email(message = "Please provide a valid email address")
    private String mail;

    private String address;

    private String city;

}