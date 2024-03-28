package com.example.backend.service;

import com.example.backend.model.Car;
import com.example.backend.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private Repository repository;
    private int idCount = 0;

    @Autowired
    public CarService(){
        this.repository = new Repository();
        this.add("Camry", "Toyota", 2020);
        this.add("Civic", "Honda", 2019);
        this.add("Mustang", "Ford", 2021);
        this.add("Model 3", "Tesla", 2022);
        this.add("Corvette", "Chevrolet", 2023);
        this.add("Accord", "Honda", 2020);
        this.add("F-150", "Ford", 2021);
        this.add("Camry", "Toyota", 2022);
        this.add("Civic", "Honda", 2023);
        this.add("Model S", "Tesla", 2020);
        this.add("Silverado", "Chevrolet", 2021);
        this.add("RAV4", "Toyota", 2022);
        this.add("CR-V", "Honda", 2023);
        this.add("Mustang", "Ford", 2020);
        this.add("Model Y", "Tesla", 2021);
        this.add("Equinox", "Chevrolet", 2022);
        this.add("Highlander", "Toyota", 2023);
        this.add("Civic", "Honda", 2020);
        this.add("F-150", "Ford", 2021);
        this.add("Model 3", "Tesla", 2022);
        this.add("3 Series", "BMW", 2023);
        this.add("A4", "Audi", 2022);
        this.add("C-Class", "Mercedes-Benz", 2021);
        this.add("X5", "BMW", 2020);
        this.add("A6", "Audi", 2019);
        this.add("E-Class", "Mercedes-Benz", 2018);
        this.add("M3", "BMW", 2017);
        this.add("Q5", "Audi", 2016);
        this.add("GLC", "Mercedes-Benz", 2015);
        this.add("X3", "BMW", 2014);
    }

    private void incrementIdCount(){this.idCount++;}

    public void add(String model, String brand, int year){
        Car car = new Car(this.idCount, model, brand, year);
        this.incrementIdCount();

        this.repository.add(car);
    }

    public void delete(Car car){
        this.repository.delete(car);
    }

    public void update(Car oldCar, String model, String brand, int year){
        this.repository.update(oldCar, model, brand, year);
    }

    public List<Car> getAll(){
        return this.repository.getAll();
    }
}
