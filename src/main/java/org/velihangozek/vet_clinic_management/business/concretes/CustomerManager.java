package org.velihangozek.vet_clinic_management.business.concretes;

import org.springframework.stereotype.Service;
import org.velihangozek.vet_clinic_management.business.abstracts.ICustomerService;
import org.velihangozek.vet_clinic_management.entities.Customer;
import org.velihangozek.vet_clinic_management.repository.CustomerRepository;

@Service
public class CustomerManager implements ICustomerService {
    private final CustomerRepository customerRepository;

    public CustomerManager(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return this.customerRepository.save(customer);
    }
}
