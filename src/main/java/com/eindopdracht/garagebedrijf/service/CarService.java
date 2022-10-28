package com.eindopdracht.garagebedrijf.service;

import com.eindopdracht.garagebedrijf.dto.CarDto;
import com.eindopdracht.garagebedrijf.exceptions.RecordNotFoundException;
import com.eindopdracht.garagebedrijf.model.Car;
import com.eindopdracht.garagebedrijf.repository.CarRepository;
import com.eindopdracht.garagebedrijf.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    public CarService(CarRepository carRepository, CustomerRepository customerRepository)
    {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    public List<CarDto> getAllCars() {
        List<Car> carList = carRepository.findAll();
        List<CarDto> carDtoList = new ArrayList<>();

        for(Car car : carList) {
            CarDto dto = transferToDto(car);
            carDtoList.add(dto);
        }
        return carDtoList;
    }

    public CarDto getCarById(Long id) {

        if (carRepository.findById(id).isPresent()) {
            Car dto = carRepository.findById(id).get();
            return transferToDto(dto);
        } else {
            throw new RecordNotFoundException("geen auto gevonden");
        }

    }

    private CarDto transferToDto(Car car) {
        CarDto dto = new CarDto();

        dto.setId(car.getId());
        dto.setBrand(car.getBrand());
        dto.setType(car.getType());

        return dto;
    }

    public Long createCar(CarDto carDto) {
        Car car = new Car();
        car.setId(carDto.id);
        car.setBrand(carDto.brand);
        car.setType(carDto.type);

        Car savedCar = this.carRepository.save(car);

        return savedCar.getId();
    }

    public void assignCustomerToCar(Long carId, Long customerId) {

        var optionalCustomer = customerRepository.findById(customerId);
        var optionalCar = carRepository.findById(carId);

        if(optionalCustomer.isPresent() && optionalCar.isPresent()) {
            var customer = optionalCustomer.get();
            var car = optionalCar.get();

            car.setCustomer(customer);
            carRepository.save(car);
        } else {
            throw new RecordNotFoundException();
        }

    }


}
