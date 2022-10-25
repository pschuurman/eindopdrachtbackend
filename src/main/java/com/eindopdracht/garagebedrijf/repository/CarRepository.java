package com.eindopdracht.garagebedrijf.repository;

import com.eindopdracht.garagebedrijf.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
