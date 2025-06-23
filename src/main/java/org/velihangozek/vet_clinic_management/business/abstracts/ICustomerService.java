package org.velihangozek.vet_clinic_management.business.abstracts;

import org.springframework.data.domain.Page;
import org.velihangozek.vet_clinic_management.entities.Customer;

import java.util.List;

public interface ICustomerService {

    Customer save(Customer customer);

    Customer get(Long id);

    List<Customer> searchByName(String name);

    Page<Customer> cursor(int page, int pageSize);

    Customer update(Customer customer);

    boolean delete(Long id);

}