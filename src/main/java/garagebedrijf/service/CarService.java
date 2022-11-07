package garagebedrijf.service;

import garagebedrijf.dto.CarDto;
import garagebedrijf.exceptions.RecordNotFoundException;
import garagebedrijf.model.Car;
import garagebedrijf.repository.CarRepository;
import garagebedrijf.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;


    public CarService(CarRepository carRepository, CustomerRepository customerRepository)
    {
        this.carRepository = carRepository;
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

    public void deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
    }

    public void updateCar(Long id, CarDto carDto) {
        if(!carRepository.existsById(id)) {
            throw new RecordNotFoundException("No car found");
        }
        Car storedCar = carRepository.findById(id).orElse(null);
        storedCar.setId(carDto.getId());
        storedCar.setBrand(carDto.getBrand());
        storedCar.setType(carDto.getType());
        carRepository.save(storedCar);
    }


    
}
