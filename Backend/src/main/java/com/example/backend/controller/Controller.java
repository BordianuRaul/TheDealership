package com.example.backend.controller;

import com.example.backend.model.Car;
import com.example.backend.model.LoginRequest;
import com.example.backend.model.User;
import com.example.backend.service.CarService;
import com.example.backend.service.DealershipService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cars")
public class Controller {
    private final CarService carService;
    private final DealershipService dealershipService;
    private final UserService userService;

    @Autowired
    public Controller(CarService service, DealershipService dealershipService, UserService userService) {
        this.carService = service;
        this.dealershipService = dealershipService;
        this.userService = userService;
    }

    @GetMapping
    public List<Car> getAllCars() {
         return carService.getAllCars();
    }

    @DeleteMapping
    public void delete(@RequestBody Car car) {
        carService.deleteCar(car);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestParam String model, @RequestParam String brand, @RequestParam Integer year) throws Exception {
        try {
            carService.saveCar(model, brand, year);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping
    public void update(@RequestBody Car car, @RequestParam String model, @RequestParam String brand, @RequestParam Integer year) throws Exception {
        carService.updateCar(car, model, brand, year);
    }

    @GetMapping("/cars/{id}")
    public Car getCarById(@PathVariable Integer id) throws Exception {
        return carService.getCarById(id);
    }

    @PostMapping("/generateRandom")
    public void generateRandomCars(@RequestParam("count") int count) {
        this.carService.generateRandomCars(count);
    }

    @GetMapping("/dealerships")
    public Map<String, List<Car>> getAllDealershipsWithCars(){
        return this.dealershipService.getAllDealershipsWithCars();
    }

    @PostMapping("/generateRandomDealerships")
    public void generateRandomDealerships(@RequestParam("count") int count){
        this.dealershipService.generateRandomDealerships(count);
    }

    @PostMapping("/generateRandomCarsForDealerships")
    public void generateRandomCarsForDealerships(@RequestParam("count") int count){
        this.dealershipService.generateRandomCarsForDealerships(count);
    }

    @GetMapping("/getCarsForDealership")
    public List<Car> getCarsForDealership(@RequestParam("id") int id){
        return this.dealershipService.getAllCarsForDealership(id);
    }

    @GetMapping("/getCarsForDealershipPagination")
    public List<Car> getCarsForDealership(@RequestParam("id") int id,
                                          @RequestParam("page") int page,
                                          @RequestParam("size") int size){
        return this.dealershipService.getCarsForDealership(id, page, size);
    }

    @PostMapping("/saveCarToDealership")
    public void saveCarToDealership(@RequestParam("dealershipId") int dealershipId,
                                    @RequestParam("model") String model,
                                    @RequestParam("brand") String brand,
                                    @RequestParam("year") int year) throws Exception {
        this.dealershipService.saveCarToDealership(dealershipId, model, brand, year);
    }

    @PostMapping("/register")
    public void registerUser(@RequestParam("username") String username,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password) throws Exception {
        this.userService.addUser(username, email, password);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        String token = userService.checkLogin(loginRequest.getEmail(), loginRequest.getPassword());
        if (token != null) {
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}
