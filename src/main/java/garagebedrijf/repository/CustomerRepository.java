package com.eindopdracht.garagebedrijf.repository;

import com.eindopdracht.garagebedrijf.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllCustomerByFirstNameEqualsIgnoreCase(String firstName);
}

