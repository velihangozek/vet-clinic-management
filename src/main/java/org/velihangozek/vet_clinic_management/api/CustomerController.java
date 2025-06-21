package org.velihangozek.vet_clinic_management.api;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.velihangozek.vet_clinic_management.business.abstracts.ICustomerService;
import org.velihangozek.vet_clinic_management.core.config.modelMapper.IModelMapperService;
import org.velihangozek.vet_clinic_management.core.result.Result;
import org.velihangozek.vet_clinic_management.core.result.ResultData;
import org.velihangozek.vet_clinic_management.core.utils.ResultHelper;
import org.velihangozek.vet_clinic_management.dto.request.customer.CustomerSaveRequest;
import org.velihangozek.vet_clinic_management.dto.request.customer.CustomerUpdateRequest;
import org.velihangozek.vet_clinic_management.dto.response.CursorResponse;
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
        CustomerResponse customerResponse = this.modelMapper.forResponse().map(savedCustomer, CustomerResponse.class);
        return ResultHelper.created(customerResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> get(@PathVariable("id") Long id) {
        Customer customer = this.customerService.get(id);
        CustomerResponse customerResponse = this.modelMapper.forResponse().map(customer, CustomerResponse.class);
        return ResultHelper.success(customerResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CustomerResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize
    ) {
        Page<Customer> customerPage = this.customerService.cursor(page, pageSize);
        Page<CustomerResponse> customerResponsePage = customerPage
                .map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class));
        return ResultHelper.cursor(customerResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@RequestBody @Valid CustomerUpdateRequest customerUpdateRequest) {
        Customer customer = this.modelMapper.forRequest().map(customerUpdateRequest, Customer.class);
        Customer updatedCustomer = this.customerService.update(customer);
        CustomerResponse customerResponse = this.modelMapper.forResponse().map(updatedCustomer, CustomerResponse.class);
        return ResultHelper.success(customerResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.customerService.delete(id);
        return ResultHelper.ok();
    }
}