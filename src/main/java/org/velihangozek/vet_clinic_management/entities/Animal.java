package org.velihangozek.vet_clinic_management.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "animals")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public enum Gender {
        MALE,
        FEMALE
    }

}