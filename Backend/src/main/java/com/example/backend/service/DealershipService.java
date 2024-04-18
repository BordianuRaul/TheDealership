package com.example.backend.service;

import com.example.backend.model.Car;
import com.example.backend.model.Dealership;
import com.example.backend.repository.CarRepository;
import com.example.backend.repository.DealershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DealershipService {

    private final DealershipRepository repository;
    private final CarRepository carRepository;

    @Autowired
    public DealershipService(DealershipRepository repository, CarRepository carRepository) {
        this.repository = repository;
        this.carRepository = carRepository;
    }

    public List<Dealership> getAllDealerships() {
        return (List<Dealership>) repository.findAll();
    }

    public Optional<Dealership> getDealershipById(int id) {
        return repository.findById(id);
    }

    public Dealership saveDealership(Dealership dealership) {
        return repository.save(dealership);
    }

    public void deleteDealership(int id) {
        repository.deleteById(id);
    }

    public Map<String, List<Car>> getAllDealershipsWithCars() {
        Map<String, List<Car>> dealershipCarsMap = new HashMap<>();
        List<Dealership> dealerships = (List<Dealership>) repository.findAll();

        for (Dealership dealership : dealerships) {
            List<Car> cars = carRepository.findByDealership(dealership);
            dealershipCarsMap.put(dealership.getName(), cars);
        }

        return dealershipCarsMap;
    }

}
