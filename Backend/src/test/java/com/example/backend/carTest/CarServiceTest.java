package com.example.backend.carTest;

import com.example.backend.model.Car;
import com.example.backend.model.Dealership;
import com.example.backend.repository.CarRepository;
import com.example.backend.service.CarService;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CarServiceTest {

    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        carService = new CarService(carRepository);
    }

    @Test
    public void testGetAllCars() throws Exception {
        // Arrange
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Model1", "Brand1", 2022, new Dealership(1, "PenAuto")));
        cars.add(new Car("Model2", "Brand2", 2023, new Dealership(2, "AutoZone")));
        when(carRepository.findAll()).thenReturn(cars);

        // Act
        List<Car> result = carService.getAllCars();

        // Assert
        assertEquals(cars.size(), result.size());
        assertEquals(cars, result);
    }

    @Test
    public void testGetCarById() throws Exception {
        // Arrange
        Car car = new Car("Model", "Brand", 2022, new Dealership(1, "PenAuto"));
        when(carRepository.findById(1)).thenReturn(Optional.of(car));

        // Act
        Car result = carService.getCarById(1);

        // Assert
        assertEquals(car, result);
    }

    @Test
    public void testSaveCar() throws Exception {
        // Arrange
        String model = "Model";
        String brand = "Brand";
        int year = 2022;
        Car car = new Car(model, brand, year, new Dealership(1, "PenAuto"));
        when(carRepository.save(car)).thenReturn(car);

        // Act
        Car result = carService.saveCar(model, brand, year);

        // Assert
        assertEquals(car, result);
    }

    @Test
    public void testDeleteCar() throws Exception {
        // Arrange
        Car car = new Car("Model", "Brand", 2022, new Dealership(1, "PenAuto"));

        // Act
        carService.deleteCar(car);

        // Assert
        verify(carRepository, times(1)).deleteById(car.getId());
    }

    // Add more test cases for generateRandomCars and updateCar if needed
}
