package org.velihangozek.vet_clinic_management.business.concretes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.velihangozek.vet_clinic_management.business.abstracts.ICustomerService;
import org.velihangozek.vet_clinic_management.core.exception.NotFoundException;
import org.velihangozek.vet_clinic_management.core.utils.Message;
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

    @Override
    public Customer get(Long id) {
        return this.customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.notFound("Customer", id)));
    }

    @Override
    public Page<Customer> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.customerRepository.findAll(pageable);
    }

    @Override
    public Customer update(Customer customer) {
        this.get(customer.getId()); // Check if customer exists
        return this.customerRepository.save(customer);
    }

    @Override
    public boolean delete(Long id) {
        Customer customer = this.get(id); // Check if customer exists
        this.customerRepository.delete(customer);
        return true;
    }
}