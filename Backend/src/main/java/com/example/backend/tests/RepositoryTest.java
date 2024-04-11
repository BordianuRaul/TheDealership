package com.example.backend.tests;

import static org.junit.jupiter.api.Assertions.*;

import com.example.backend.model.Car;
import com.example.backend.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class RepositoryTest {

    private Repository repository;

    @BeforeEach
    public void setUp() {
        repository = new Repository();
    }

    @Test
    public void testAdd() throws Exception {

        Car car = new Car(1, "Toyota Camry", "Toyota", 2022);

        repository.add(car);
        List<Car> cars = repository.getAll();


        assertEquals(1, cars.size());
        assertEquals(car, cars.get(0));
    }

    @Test
    public void testUpdate() throws Exception {

        Car car = new Car(1, "Toyota Camry", "Toyota", 2022);
        repository.add(car);


        repository.update(car, "Honda Civic", "Honda", 2023);
        Car updatedCar = repository.getCarByID(1);


        assertEquals("Honda Civic", updatedCar.getModel());
        assertEquals("Honda", updatedCar.getBrand());
        assertEquals(2023, updatedCar.getYear());
    }

    @Test
    public void testDelete() throws Exception {

        Car car = new Car(1, "Toyota Camry", "Toyota", 2022);
        repository.add(car);


        repository.delete(car);
        List<Car> cars = repository.getAll();


        assertTrue(cars.isEmpty());
    }

    @Test
    public void testGetAll() throws Exception {

        Car car1 = new Car(1, "Toyota Camry", "Toyota", 2022);
        Car car2 = new Car(2, "Honda Civic", "Honda", 2023);
        repository.add(car1);
        repository.add(car2);


        List<Car> cars = repository.getAll();


        assertEquals(2, cars.size());
        assertTrue(cars.contains(car1));
        assertTrue(cars.contains(car2));
    }

    @Test
    public void testGetCarByID() throws Exception {

        Car car1 = new Car(1, "Toyota Camry", "Toyota", 2022);
        Car car2 = new Car(2, "Honda Civic", "Honda", 2023);
        repository.add(car1);
        repository.add(car2);


        Car retrievedCar1 = repository.getCarByID(1);
        Car retrievedCar2 = repository.getCarByID(2);
        Car retrievedCar3 = repository.getCarByID(3);


        assertEquals(car1, retrievedCar1);
        assertEquals(car2, retrievedCar2);
        assertNotEquals(-1, retrievedCar1.getId());
        assertEquals(-1, retrievedCar3.getId());
    }
}
