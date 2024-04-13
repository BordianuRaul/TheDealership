package com.example.backend.controller;

import com.example.backend.model.Car;
import com.example.backend.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cars")
public class Controller {
    private final CarService carService;

    @Autowired
    public Controller(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAll();
    }

    @DeleteMapping
    public void delete(@RequestBody Car car) {
        carService.delete(car);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestParam String model, @RequestParam String brand, @RequestParam Integer year) throws Exception {
        try {
            carService.add(model, brand, year);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping
    public void update(@RequestBody Car car, @RequestParam String model, @RequestParam String brand, @RequestParam Integer year){
        carService.update(car, model, brand, year);
    }

    @GetMapping("/cars/{id}")
    public Car getCarById(@PathVariable Integer id) throws Exception {
        return carService.getCarById(id);
    }

    @PostMapping("/generateRandom")
    public void generateRandomCars(@RequestParam("count") int count) {
        this.carService.generateRandomCars(count);
    }
}
