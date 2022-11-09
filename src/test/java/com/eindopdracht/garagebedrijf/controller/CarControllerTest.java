package com.eindopdracht.garagebedrijf.controller;

import com.eindopdracht.garagebedrijf.dto.CarDto;
import com.eindopdracht.garagebedrijf.security.JwtService;
import com.eindopdracht.garagebedrijf.service.CarService;
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


@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtService jwtService;

    @MockBean
    CarService carService;

    @Test
    @WithMockUser(username ="testuser", roles ="ADMINISTRATIEF MEDEWERKER")
    void ShouldGetCorrectCar() throws Exception {

        CarDto carDto = new CarDto();
        carDto.brand = "Mazda";
        carDto.type = "Premacy";

        Mockito.when(carService.getCarById(1L)).thenReturn(carDto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/cars/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand", is("Mazda")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", is("Premacy")));

    }
}