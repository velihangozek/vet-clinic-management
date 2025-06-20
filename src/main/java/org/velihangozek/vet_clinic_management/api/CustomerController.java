package org.velihangozek.vet_clinic_management.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.velihangozek.vet_clinic_management.business.abstracts.ICustomerService;
import org.velihangozek.vet_clinic_management.core.config.modelMapper.IModelMapperService;
import org.velihangozek.vet_clinic_management.core.result.Result;
import org.velihangozek.vet_clinic_management.core.result.ResultData;
import org.velihangozek.vet_clinic_management.core.utils.ResultHelper;
import org.velihangozek.vet_clinic_management.dto.request.customer.CustomerSaveRequest;
import org.velihangozek.vet_clinic_management.dto.response.customer.CustomerResponse;
import org.velihangozek.vet_clinic_management.entities.Customer;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;

    public CustomerController(ICustomerService customerService, IModelMapperService modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@RequestBody @Valid CustomerSaveRequest customerSaveRequest) {
        Customer customer = this.modelMapper.forRequest().map(customerSaveRequest, Customer.class);
        Customer savedCustomer = this.customerService.save(customer);
        return ResultHelper.created(this.modelMapper.forResponse().map(savedCustomer, CustomerResponse.class));
    }
}