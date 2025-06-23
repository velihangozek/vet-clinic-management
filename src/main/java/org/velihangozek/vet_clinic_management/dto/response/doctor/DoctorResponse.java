package org.velihangozek.vet_clinic_management.dto.response.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.velihangozek.vet_clinic_management.dto.response.availabledate.AvailableDateResponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {

    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
    private List<AvailableDateResponse> availableDates;

}