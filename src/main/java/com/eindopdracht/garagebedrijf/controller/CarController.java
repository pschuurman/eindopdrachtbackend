package com.eindopdracht.garagebedrijf.controller;

import com.eindopdracht.garagebedrijf.dto.CarDto;
import com.eindopdracht.garagebedrijf.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) { this.carService = carService; }

   @PostMapping("/cars")
    public ResponseEntity<Long> createCar(@RequestBody CarDto carDto) {
        Long carId = carService.createCar(carDto);
        return new ResponseEntity<>(carId, HttpStatus.CREATED);
    }

    @PutMapping("/cars/{carId}/{customerId}")
    public void assignCustomerToCar(@PathVariable("carId") Long carId, @PathVariable("customerId") Long customerId) {
        carService.assignCustomerToCar(carId, customerId);

    }
}
