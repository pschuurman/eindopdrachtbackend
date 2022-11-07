package com.eindopdracht.garagebedrijf.controller;

import garagebedrijf.controller.CustomerController;
import garagebedrijf.dto.CustomerDto;
import garagebedrijf.security.JwtService;
import garagebedrijf.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtService jwtService;

    @MockBean
    CustomerService customerService;


    @Test
    @WithMockUser(username ="testuser", roles ="ADMINISTRATIEF MEDEWERKER")
    void ShouldGetCorrectCustomer() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.firstName = "Peter";
        customerDto.lastName = "Schuurman";
        customerDto.street = "Appelstrooplaan";
        customerDto.houseNumber = 135;
        customerDto.postalCode = "1234 AB";

        Mockito.when(customerService.getCustomerById(1L)).thenReturn(customerDto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/customers/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is("Peter")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", is("Schuurman")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.street", is("Appelstrooplaan")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.houseNumber", is(135)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.postalCode", is("1234 AB")));

    }
}