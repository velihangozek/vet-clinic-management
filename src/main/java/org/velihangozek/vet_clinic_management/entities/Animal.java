package org.velihangozek.vet_clinic_management.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "animals")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "appointments")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long id;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "animal_customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @NotBlank
    @Column(name = "animal_name")
    private String name;

    @NotBlank
    @Column(name = "animal_species")
    private String species;

    @NotBlank
    @Column(name = "animal_breed")
    private String breed;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "animal_gender")
    private Gender gender;

    @Column(name = "animal_color")
    private String color;

    @PastOrPresent
    @Column(name = "animal_date_of_birth")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnimalVaccine> vaccinations = new ArrayList<>();

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

    public enum Gender {
        MALE,
        FEMALE
    }

}