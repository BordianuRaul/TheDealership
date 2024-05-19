package com.example.backend.service;

import com.example.backend.model.Car;
import com.example.backend.model.Dealership;
import com.example.backend.repository.CarRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    final private Dealership dealership = new Dealership(1,"PenAuto");

    private final Faker faker;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.faker = new Faker(Locale.US);
    }

    public List<Car> getAllCars() {
        return (List<Car>) carRepository.findAll();
    }

    public Car getCarById(int id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car saveCar(String model, String brand, int year) throws Exception {
        Car car = new Car(model, brand, year, dealership);
        return carRepository.save(car);
    }

    public void deleteCar(Car car) {
        carRepository.deleteById(car.getId());
    }

    public void generateRandomCars(int count) {
        for (int i = 0; i < count; i++) {
            String makeAndModelString = faker.vehicle().makeAndModel();
            String[] makeAndModel = makeAndModelString.split(" ");
            String model = makeAndModel[1];
            String brand = makeAndModel[0];
            int year = faker.number().numberBetween(1990, 2024);
            try {
                this.saveCar(model, brand, year);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    public Car updateCar(Car car, String model, String brand, int year) throws Exception {
        Optional<Car> optionalCar = carRepository.findById(car.getId());
        if (optionalCar.isPresent()) {
            Car newCar = optionalCar.get();

            newCar.setModel(model);
            newCar.setBrand(brand);
            newCar.setYear(year);

            return carRepository.save(newCar);
        } else {
            throw new IllegalArgumentException("Car with ID " + car.getId() + " not found");
        }
    }


}