package com.example.backend.dealershipTest;

import com.example.backend.model.Dealership;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DealershipTest {

    @Test
    public void testConstructorWithName() {
        String name = "PenAuto";

        Dealership dealership = new Dealership(name);

        assertEquals(name, dealership.getName());
    }

    @Test
    public void testConstructorWithIdAndName() {

        int id = 1;
        String name = "PenAuto";


        Dealership dealership = new Dealership(id, name);


        assertEquals(id, dealership.getId());
        assertEquals(name, dealership.getName());
    }

    @Test
    public void testDefaultConstructor() {

        Dealership dealership = new Dealership();

        assertNotNull(dealership);
    }

    @Test
    public void testSettersAndGetters() {

        int id = 1;
        String name = "PenAuto";
        Dealership dealership = new Dealership();

        dealership.setId(id);
        dealership.setName(name);

        assertEquals(id, dealership.getId());
        assertEquals(name, dealership.getName());
    }
}
