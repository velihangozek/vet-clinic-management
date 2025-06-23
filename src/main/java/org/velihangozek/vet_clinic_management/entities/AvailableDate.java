package org.velihangozek.vet_clinic_management.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "available_dates")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "doctors")
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_date_id")
    private Long id;

    @NotNull
    @Column(name = "available_date")
    private LocalDate availableDate;

    @ManyToMany(mappedBy = "availableDates", fetch = FetchType.LAZY)
    private Set<Doctor> doctors = new HashSet<>();

    public AvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
    }

}