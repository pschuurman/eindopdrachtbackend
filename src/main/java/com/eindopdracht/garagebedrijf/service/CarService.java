package com.eindopdracht.garagebedrijf.service;

import com.eindopdracht.garagebedrijf.dto.CarDto;
import com.eindopdracht.garagebedrijf.exceptions.RecordNotFoundException;
import com.eindopdracht.garagebedrijf.model.Car;
import com.eindopdracht.garagebedrijf.repository.CarRepository;
import com.eindopdracht.garagebedrijf.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
@Service
public class CarService {

    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    public CarService(CarRepository carRepository, CustomerRepository customerRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    public List<CarDto> getAllCars() {
        List<Car> carList = carRepository.findAll();
        List<CarDto> carDtoList = new ArrayList<>();

        for (Car car : carList) {
            CarDto dto = transferToDto(car);
            carDtoList.add(dto);
        }
        return carDtoList;
    }

    private CarDto transferToDto(Car car) {
        CarDto dto = new CarDto();

        dto.setId(car.getId());
        dto.setBrand(car.getBrand());
        dto.setType(car.getType());
        ;

        return dto;
    }


    public CarDto getCarById(Long id) {

        if (carRepository.findById(id).isPresent()) {
            Car dto = carRepository.findById(id).get();
            return transferToDto(dto);
        } else {
            throw new RecordNotFoundException("geen auto gevonden");
        }

    }


    public Long createCar(CarDto carDto) {
        Car car = new Car();
        car.setId(carDto.id);
        car.setBrand(carDto.brand);
        car.setType(carDto.type);

        Car savedCar = this.carRepository.save(car);

        return savedCar.getId();
    }

    public void deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
    }

    public void updateCar(Long id, CarDto carDto) {
        if (!carRepository.existsById(id)) {
            throw new RecordNotFoundException("No car found");
        }
        Car storedCar = carRepository.findById(id).orElse(null);
        storedCar.setId(carDto.getId());
        storedCar.setBrand(carDto.getBrand());
        storedCar.setType(carDto.getType());
        carRepository.save(storedCar);
    }

    public void assignCustomerToCar(Long id, Long customerId) {
        var optionalCar = carRepository.findById(id);
        var optionalCustomer = customerRepository.findById(customerId);

        if (optionalCar.isPresent() && optionalCustomer.isPresent()) {
            var car = optionalCar.get();
            var customer = optionalCustomer.get();

            car.setCustomer(customer);
            carRepository.save(car);
        } else {
            throw new RecordNotFoundException();
        }


    }

}
