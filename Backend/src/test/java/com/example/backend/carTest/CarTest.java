package com.example.backend.carTest;

import com.example.backend.model.Car;
import com.example.backend.model.Dealership;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.example.backend.model.Car;
import com.example.backend.model.Dealership;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    @Test
    public void testConstructorWithValidData() throws Exception {
        // Arrange
        String model = "Model";
        String brand = "Brand";
        int year = 2022;
        Dealership dealership = new Dealership("PenAuto");

        // Act
        Car car = new Car(model, brand, year, dealership);

        // Assert
        assertEquals(model, car.getModel());
        assertEquals(brand, car.getBrand());
        assertEquals(year, car.getYear());
        assertEquals(dealership, car.getDealership());
    }

    @Test
    public void testConstructorWithInvalidModel() {
        // Arrange
        String model = "";
        String brand = "Brand";
        int year = 2022;
        Dealership dealership = new Dealership("PenAuto");

        // Act & Assert
        assertThrows(Exception.class, () -> new Car(model, brand, year, dealership));
    }

    @Test
    public void testConstructorWithInvalidBrand() {
        // Arrange
        String model = "Model";
        String brand = "";
        int year = 2022;
        Dealership dealership = new Dealership("PenAuto");

        // Act & Assert
        assertThrows(Exception.class, () -> new Car(model, brand, year, dealership));
    }

    @Test
    public void testConstructorWithInvalidYear() {
        // Arrange
        String model = "Model";
        String brand = "Brand";
        int year = 1768; // Invalid year
        Dealership dealership = new Dealership("PenAuto");

        // Act & Assert
        assertThrows(Exception.class, () -> new Car(model, brand, year, dealership));
    }

    @Test
    public void testConstructorWithMustangAndNonFordBrand() {
        // Arrange
        String model = "Mustang";
        String brand = "Chevrolet"; // Non-Ford brand
        int year = 2022;
        Dealership dealership = new Dealership("PenAuto");

        // Act & Assert
        assertThrows(Exception.class, () -> new Car(model, brand, year, dealership));
    }

    @Test
    public void testDefaultConstructor() {
        // Act
        Car car = new Car();

        // Assert
        assertNotNull(car);
    }

    @Test
    public void testSettersAndGetters() throws Exception {

        String model = "Model";
        String brand = "Brand";
        int year = 2022;

        Dealership dealership = new Dealership("PenAuto");
        Car car = new Car();


        car.setModel(model);
        car.setBrand(brand);
        car.setYear(year);

        assertEquals(model, car.getModel());
        assertEquals(brand, car.getBrand());
        assertEquals(year, car.getYear());
    }

    @Test
    public void testEqualsAndHashCode() throws Exception {
        // Arrange
        String model = "Model";
        String brand = "Brand";
        int year = 2022;
        Dealership dealership = new Dealership("PenAuto");
        Car car1 = new Car(model, brand, year, dealership);
        Car car2 = new Car(model, brand, year, dealership);

        // Act & Assert
        assertTrue(car1.equals(car2));

    }

    @Test
    public void testToString() throws Exception {
        // Arrange
        String model = "Model";
        String brand = "Brand";
        int year = 2022;
        Dealership dealership = new Dealership("PenAuto");
        Car car = new Car(model, brand, year, dealership);

        // Act & Assert
        assertEquals("id = 0 model = Model brand = Brand year = 2022", car.toString());
    }
}
