package com.example.backend.repository;

import com.example.backend.model.Car;
import com.example.backend.model.Dealership;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Integer> {
    List<Car> findByDealership(Dealership dealership);
    Page<Car> findByDealership(Dealership dealership, Pageable pageable);

}
