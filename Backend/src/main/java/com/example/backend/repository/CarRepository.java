package com.example.backend.repository;

import com.example.backend.model.Car;
import com.example.backend.model.Dealership;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Integer> {
    List<Car> findByDealership(Dealership dealership);

}
