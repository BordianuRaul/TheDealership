package com.example.backend.dealershipTest;

import com.example.backend.model.Dealership;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DealershipTest {

    @Test
    public void testConstructorWithName() {
        // Arrange
        String name = "PenAuto";

        // Act
        Dealership dealership = new Dealership(name);

        // Assert
        assertEquals(name, dealership.getName());
    }

    @Test
    public void testConstructorWithIdAndName() {
        // Arrange
        int id = 1;
        String name = "PenAuto";

        // Act
        Dealership dealership = new Dealership(id, name);

        // Assert
        assertEquals(id, dealership.getId());
        assertEquals(name, dealership.getName());
    }

    @Test
    public void testDefaultConstructor() {
        // Act
        Dealership dealership = new Dealership();

        // Assert
        assertNotNull(dealership);
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        int id = 1;
        String name = "PenAuto";
        Dealership dealership = new Dealership();

        // Act
        dealership.setId(id);
        dealership.setName(name);

        // Assert
        assertEquals(id, dealership.getId());
        assertEquals(name, dealership.getName());
    }
}
