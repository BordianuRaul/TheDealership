package com.example.backend.service;

import com.example.backend.model.Car;
import com.example.backend.model.Dealership;
import com.example.backend.repository.CarRepository;
import com.example.backend.repository.DealershipRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

@Service
public class DealershipService {

    private final DealershipRepository repository;
    private final CarRepository carRepository;

    private final Faker faker;

    @Autowired
    public DealershipService(DealershipRepository repository, CarRepository carRepository) {
        this.repository = repository;
        this.carRepository = carRepository;
        this.faker = new Faker(Locale.ITALIAN);
    }

    public List<Dealership> getAllDealerships() {
        return (List<Dealership>) repository.findAll();
    }

    public Optional<Dealership> getDealershipById(int id) {
        return repository.findById(id);
    }

    public Dealership saveDealership(String name) {
        Dealership dealership = new Dealership(name);
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

    public void generateRandomDealerships(int count){
        for(int i = 0; i < count; i++){
            String name = faker.address().cityName();
            name = name + "Auto";
            try {
                this.saveDealership(name);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void generateRandomCarsForDealerships(int count){
        for (int i = 0; i < count; i++) {
            String makeAndModelString = faker.vehicle().makeAndModel();
            String[] makeAndModel = makeAndModelString.split(" ");
            String model = makeAndModel[1];
            String brand = makeAndModel[0];
            int year = faker.number().numberBetween(1990, 2024);
            Random random = new Random();
            int randomId = 1000;
            Dealership dealership = this.getDealershipById(randomId).get();
            try {
                Car car = new Car(model, brand, year, dealership);
                this.carRepository.save(car);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveCarToDealership(int dealershipId, String model, String brand, int year) throws Exception {
        Dealership dealership = this.getDealershipById(dealershipId).get();
        Car car = new Car(model, brand, year, dealership);
        this.carRepository.save(car);
    }

    public List<Car> getAllCarsForDealership(int id){
        Dealership dealership = this.getDealershipById(id).get();
        return this.carRepository.findByDealership(dealership);
    }

    public List<Car> getCarsForDealership(int id, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Dealership dealership = this.getDealershipById(id).orElseThrow(() -> new RuntimeException("Dealership not found with ID: " + id));
        Page<Car> carPage = carRepository.findByDealership(dealership, pageable);
        return carPage.getContent();
    }


}
