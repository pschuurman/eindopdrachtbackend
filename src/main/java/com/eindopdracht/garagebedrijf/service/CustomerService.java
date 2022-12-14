package com.eindopdracht.garagebedrijf.service;

import com.eindopdracht.garagebedrijf.dto.CustomerDto;
import com.eindopdracht.garagebedrijf.exceptions.RecordNotFoundException;
import com.eindopdracht.garagebedrijf.model.Customer;
import com.eindopdracht.garagebedrijf.repository.CarRepository;
import com.eindopdracht.garagebedrijf.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

    public List<CustomerDto> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> customerDtoList = new ArrayList<>();

        for (Customer customer : customerList) {
            CustomerDto dto = transferToDto(customer);
            customerDtoList.add(dto);
        }
        return customerDtoList;
    }

    public CustomerDto getCustomerById(Long id) {

        if (customerRepository.findById(id).isPresent()) {
            Customer dto = customerRepository.findById(id).get();
            return transferToDto(dto);
        } else {
            throw new RecordNotFoundException("geen klant gevonden");
        }
    }

    public void updateCustomer(Long id, CustomerDto customerDto) {
        if(!customerRepository.existsById(id)) {
            throw new RecordNotFoundException("No customer found");
        }
        Customer storedCustomer = customerRepository.findById(id).orElse(null);
        storedCustomer.setId(customerDto.getId());
        storedCustomer.setFirstName(customerDto.getFirstName());
        storedCustomer.setLastName(customerDto.getLastName());
        storedCustomer.setStreet(customerDto.getStreet());
        storedCustomer.setHouseNumber(customerDto.getHouseNumber());
        storedCustomer.setPostalCode(customerDto.getPostalCode());
        customerRepository.save(storedCustomer);
    }

    public void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }



    private CustomerDto transferToDto(Customer customer) {
        CustomerDto dto = new CustomerDto();

        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setStreet(customer.getStreet());
        dto.setHouseNumber(customer.getHouseNumber());
        dto.setPostalCode(customer.getPostalCode());

        return dto;
    }

    public Long createCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.firstName);
        customer.setLastName(customerDto.lastName);
        customer.setStreet(customerDto.street);
        customer.setHouseNumber(customerDto.houseNumber);
        customer.setPostalCode(customerDto.postalCode);


        Customer savedCustomer = this.customerRepository.save(customer);

        return savedCustomer.getId();

    }





}

