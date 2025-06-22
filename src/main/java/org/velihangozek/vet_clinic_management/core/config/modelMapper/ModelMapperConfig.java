package org.velihangozek.vet_clinic_management.core.config.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.velihangozek.vet_clinic_management.dto.request.animal.AnimalSaveRequest;
import org.velihangozek.vet_clinic_management.entities.Animal;

@Configuration
public class ModelMapperConfig {

    // IMPORTANT: Always skip id mapping in SaveRequest to Entity TypeMaps
    // to avoid accidental assignment of unrelated fields (e.g., customerId → id).

    @Bean
    public ModelMapper getModelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        // Prevent accidental id mapping for creation DTOs - AnimalSaveRequest -> Animal: SKIP id mapping!
        modelMapper.createTypeMap(AnimalSaveRequest.class, Animal.class)
                .addMappings(mapper -> mapper.skip(Animal::setId));
        // Repeat for each entity as needed

        return modelMapper;
    }
}