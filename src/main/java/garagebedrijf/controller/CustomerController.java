package garagebedrijf.controller;

import garagebedrijf.dto.CustomerDto;
import garagebedrijf.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customerDtoList;
        customerDtoList = customerService.getAllCustomers();

        return ResponseEntity.ok().body(customerDtoList);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") Long id) {
        CustomerDto customerDto = customerService.getCustomerById(id);
        return ResponseEntity.ok().body(customerDto);

    }

    @PostMapping("/customers")
    public ResponseEntity<Long> createCustomer(@RequestBody CustomerDto customerDto) {
        Long customerId = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(customerId, HttpStatus.CREATED);
    }

    @PutMapping("customers/{id}")
    public CustomerDto updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(id, customerDto);
        return customerDto;
    }


    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}

