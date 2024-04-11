package com.example.backend.tests;

import static org.junit.jupiter.api.Assertions.*;

import com.example.backend.model.Car;
import com.example.backend.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CarServiceTest {

    private CarService carService;

    @BeforeEach
    public void setUp() throws Exception {
        carService = new CarService();
    }

    @Test
    public void testAdd() throws Exception {

        int initialSize = carService.getAll().size();
        Car car = new Car(30, "TestModel", "TestBrand", 2022);


        carService.add("TestModel", "TestBrand", 2022);
        List<Car> cars = carService.getAll();


        assertEquals(initialSize + 1, cars.size());
        assertTrue(cars.contains(car));
    }

    @Test
    public void testDelete() throws Exception {

        Car car = new Car(1, "Toyota Camry", "Toyota", 2022);
        carService.add("Toyota Camry", "Toyota", 2022);
        int initialSize = carService.getAll().size();


        carService.delete(car);
        List<Car> cars = carService.getAll();


        assertEquals(initialSize - 1, cars.size());
        assertFalse(cars.contains(car));
    }

    @Test
    public void testUpdate() throws Exception {

        Car car = new Car(1, "Toyota Camry", "Toyota", 2022);
        carService.add("Toyota Camry", "Toyota", 2022);


        carService.update(car, "Honda Civic", "Honda", 2023);
        Car updatedCar = carService.getCarById(1);


        assertEquals("Honda Civic", updatedCar.getModel());
        assertEquals("Honda", updatedCar.getBrand());
        assertEquals(2023, updatedCar.getYear());
    }

    @Test
    public void testGetAll() {

        List<Car> cars = carService.getAll();


        assertEquals(30, cars.size());
    }

    @Test
    public void testGetCarById() throws Exception {

        Car car = new Car(1, "Toyota Camry", "Toyota", 2022);
        carService.add("Toyota Camry", "Toyota", 2022);

        Car retrievedCar = carService.getCarById(1);

        assertEquals(car, retrievedCar);
    }
}
