package com.eindopdracht.garagebedrijf.repository;

import com.eindopdracht.garagebedrijf.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

