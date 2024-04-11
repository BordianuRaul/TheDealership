package com.example.backend.tests;

import static org.junit.jupiter.api.Assertions.*;

import com.example.backend.model.Car;
import org.junit.jupiter.api.Test;

public class CarTest {

    @Test
    public void testConstructorAndGetters() throws Exception {

        int id = 1;
        String model = "Toyota Camry";
        String brand = "Toyota";
        int year = 2022;


        Car car = new Car(id, model, brand, year);


        assertEquals(id, car.getId());
        assertEquals(model, car.getModel());
        assertEquals(brand, car.getBrand());
        assertEquals(year, car.getYear());
    }

    @Test
    public void testSetters() throws Exception {

        Car car = new Car(1, "Toyota Camry", "Toyota", 2022);


        car.setId(2);
        car.setModel("Honda Civic");
        car.setBrand("Honda");
        car.setYear(2023);


        assertEquals(2, car.getId());
        assertEquals("Honda Civic", car.getModel());
        assertEquals("Honda", car.getBrand());
        assertEquals(2023, car.getYear());
    }

    @Test
    public void testToString() throws Exception {

        Car car = new Car(1, "Toyota Camry", "Toyota", 2022);


        String str = car.toString();


        assertTrue(str.contains("id = 1"));
        assertTrue(str.contains("model = Toyota Camry"));
        assertTrue(str.contains("brand = Toyota"));
        assertTrue(str.contains("year = 2022"));
    }

    @Test
    public void testEquals() throws Exception {

        Car car1 = new Car(1, "Toyota Camry", "Toyota", 2022);
        Car car2 = new Car(1, "Toyota Camry", "Toyota", 2022);
        Car car3 = new Car(2, "Honda Civic", "Honda", 2023);


        assertTrue(car1.equals(car2));
        assertFalse(car1.equals(car3));
    }
}
