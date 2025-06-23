package org.velihangozek.vet_clinic_management.api;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.velihangozek.vet_clinic_management.business.abstracts.IAnimalService;
import org.velihangozek.vet_clinic_management.business.abstracts.ICustomerService;
import org.velihangozek.vet_clinic_management.core.config.modelMapper.IModelMapperService;
import org.velihangozek.vet_clinic_management.core.result.Result;
import org.velihangozek.vet_clinic_management.core.result.ResultData;
import org.velihangozek.vet_clinic_management.core.utils.ResultHelper;
import org.velihangozek.vet_clinic_management.dto.request.animal.AnimalSaveRequest;
import org.velihangozek.vet_clinic_management.dto.request.animal.AnimalUpdateRequest;
import org.velihangozek.vet_clinic_management.dto.response.CursorResponse;
import org.velihangozek.vet_clinic_management.dto.response.animal.AnimalResponse;
import org.velihangozek.vet_clinic_management.dto.response.customer.CustomerResponse;
import org.velihangozek.vet_clinic_management.entities.Animal;
import org.velihangozek.vet_clinic_management.entities.Customer;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {
    private final IAnimalService animalService;
    private final IModelMapperService modelMapper;
    private final ICustomerService customerService;

    public AnimalController(
            IAnimalService animalService,
            IModelMapperService modelMapper,
            ICustomerService customerService) {
        this.animalService = animalService;
        this.modelMapper = modelMapper;
        this.customerService = customerService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@RequestBody @Valid AnimalSaveRequest animalSaveRequest) {
        Animal animal = this.modelMapper.forRequest().map(animalSaveRequest, Animal.class);
        Customer customer = this.customerService.get(animalSaveRequest.getCustomerId());
        animal.setCustomer(customer);
        // System.out.println("animal.getId() before save: " + animal.getId()); // DEBUG
        Animal savedAnimal = this.animalService.save(animal);
        AnimalResponse animalResponse = this.modelMapper.forResponse().map(savedAnimal, AnimalResponse.class);
        return ResultHelper.created(animalResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") Long id) {
        Animal animal = this.animalService.get(id);
        AnimalResponse animalResponse = this.modelMapper.forResponse().map(animal, AnimalResponse.class);
        return ResultHelper.success(animalResponse);
    }

    @GetMapping("/{id}/customer")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> getCustomer(@PathVariable("id") Long id) {
        Animal animal = this.animalService.get(id);
        CustomerResponse customerResponse = this.modelMapper.forResponse().map(animal.getCustomer(), CustomerResponse.class);
        return ResultHelper.success(customerResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize
    ) {
        Page<Animal> animalPage = this.animalService.cursor(page, pageSize);
        Page<AnimalResponse> animalResponsePage = animalPage
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class));
        return ResultHelper.cursor(animalResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@RequestBody @Valid AnimalUpdateRequest animalUpdateRequest) {
        Animal animal = this.modelMapper.forRequest().map(animalUpdateRequest, Animal.class);
        Customer customer = this.customerService.get(animalUpdateRequest.getCustomerId()); // Check if the customer exists
        animal.setCustomer(customer);
        Animal updatedAnimal = this.animalService.update(animal);
        AnimalResponse animalResponse = this.modelMapper.forResponse().map(updatedAnimal, AnimalResponse.class);
        return ResultHelper.success(animalResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.animalService.delete(id);
        return ResultHelper.ok();
    }

}