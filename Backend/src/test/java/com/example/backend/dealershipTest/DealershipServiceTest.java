package com.example.backend.dealershipTest;

import com.example.backend.model.Car;
import com.example.backend.model.Dealership;
import com.example.backend.repository.CarRepository;
import com.example.backend.repository.DealershipRepository;
import com.example.backend.service.DealershipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DealershipServiceTest {

    @Mock
    private DealershipRepository dealershipRepository;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private DealershipService dealershipService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllDealerships() {
        List<Dealership> expectedDealerships = new ArrayList<>();
        when(dealershipRepository.findAll()).thenReturn(expectedDealerships);

        List<Dealership> actualDealerships = dealershipService.getAllDealerships();

        assertEquals(expectedDealerships, actualDealerships);
    }

    @Test
    void testGetDealershipById() {
        int id = 1;
        Dealership expectedDealership = new Dealership(1, "Test Dealership");
        when(dealershipRepository.findById(id)).thenReturn(Optional.of(expectedDealership));

        Optional<Dealership> actualDealershipOptional = dealershipService.getDealershipById(id);

        assertEquals(expectedDealership, actualDealershipOptional.orElse(null));
    }

    @Test
    void testSaveDealership() {
        Dealership dealershipToSave = new Dealership(1, "Test Dealership");
        when(dealershipRepository.save(any())).thenReturn(dealershipToSave);

        Dealership savedDealership = dealershipService.saveDealership(dealershipToSave);

        assertEquals(dealershipToSave, savedDealership);
        verify(dealershipRepository, times(1)).save(any());
    }

    @Test
    void testDeleteDealership() {

        int id = 1;

        dealershipService.deleteDealership(id);

        verify(dealershipRepository, times(1)).deleteById(id);
    }

    @Test
    void testGetAllDealershipsWithCars() throws Exception {

        Dealership dealership1 = new Dealership(1, "Dealership 1");
        Dealership dealership2 = new Dealership(2, "Dealership 2");

        List<Dealership> dealerships = new ArrayList<>();
        dealerships.add(dealership1);
        dealerships.add(dealership2);

        Car car1 = new Car("Car 1", "Brand 1", 2022, dealership1);
        Car car2 = new Car("Car 2", "Brand 2", 2023, dealership1);
        Car car3 = new Car("Car 3", "Brand 3", 2021, dealership2);

        List<Car> carsForDealership1 = new ArrayList<>();
        carsForDealership1.add(car1);
        carsForDealership1.add(car2);

        List<Car> carsForDealership2 = new ArrayList<>();
        carsForDealership2.add(car3);

        when(dealershipRepository.findAll()).thenReturn(dealerships);
        when(carRepository.findByDealership(dealership1)).thenReturn(carsForDealership1);
        when(carRepository.findByDealership(dealership2)).thenReturn(carsForDealership2);


        Map<String, List<Car>> dealershipCarsMap = dealershipService.getAllDealershipsWithCars();


        assertEquals(2, dealershipCarsMap.size());
        assertEquals(carsForDealership1, dealershipCarsMap.get(dealership1.getName()));
        assertEquals(carsForDealership2, dealershipCarsMap.get(dealership2.getName()));
    }


}
