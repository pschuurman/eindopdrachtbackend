package garagebedrijf.controller;

import garagebedrijf.dto.CarDto;
import garagebedrijf.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> carDtoList;
        carDtoList = carService.getAllCars();

        return ResponseEntity.ok().body(carDtoList);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable("id")Long id) {
        CarDto car = carService.getCarById(id);

        return ResponseEntity.ok().body(car);
    }

    @PostMapping("/cars")
    public ResponseEntity<Long> createCar(@RequestBody CarDto carDto) {
        Long carId = carService.createCar(carDto);
        return new ResponseEntity<>(carId, HttpStatus.CREATED);
    }

    @PutMapping("/cars/{id}")
    public CarDto updateCar(@PathVariable("id") Long id, @RequestBody CarDto carDto) {
        carService.updateCar(id, carDto);
        return carDto;
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Long> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }



}

