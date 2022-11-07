package com.eindopdracht.garagebedrijf.service;

import com.eindopdracht.garagebedrijf.dto.CustomerDto;
import com.eindopdracht.garagebedrijf.exceptions.RecordNotFoundException;
import com.eindopdracht.garagebedrijf.model.Customer;
import com.eindopdracht.garagebedrijf.repository.CarRepository;
import com.eindopdracht.garagebedrijf.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    public CustomerService(CustomerRepository customerRepository, CarRepository carRepository) {
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
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

    public void deleteCustomer(@RequestBody Long id) {
        customerRepository.deleteById(id);
    }

    public CustomerDto getCustomerById(Long id) {

        if (customerRepository.findById(id).isPresent()) {
            Customer dto = customerRepository.findById(id).get();
            return transferToDto(dto);
        } else {
            throw new RecordNotFoundException("geen klant gevonden");
        }
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

