package com.example.backend.dealershipTest;

import com.example.backend.model.Dealership;
import com.example.backend.repository.DealershipRepository;
import com.example.backend.service.DealershipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DealershipServiceTest {

    private DealershipService dealershipService;

    @Mock
    private DealershipRepository dealershipRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        dealershipService = new DealershipService(dealershipRepository);
    }

    @Test
    public void testGetAllDealerships() {
        // Arrange
        List<Dealership> dealerships = new ArrayList<>();
        dealerships.add(new Dealership(1, "PenAuto"));
        dealerships.add(new Dealership(2, "AutoZone"));
        when(dealershipRepository.findAll()).thenReturn(dealerships);

        // Act
        List<Dealership> result = dealershipService.getAllDealerships();

        // Assert
        assertEquals(dealerships.size(), result.size());
        assertEquals(dealerships, result);
    }

    @Test
    public void testGetDealershipById() {
        // Arrange
        Dealership dealership = new Dealership(1, "PenAuto");
        when(dealershipRepository.findById(1)).thenReturn(Optional.of(dealership));

        // Act
        Optional<Dealership> result = dealershipService.getDealershipById(1);

        // Assert
        assertEquals(dealership, result.orElse(null));
    }

    @Test
    public void testSaveDealership() {
        // Arrange
        Dealership dealership = new Dealership(1, "PenAuto");
        when(dealershipRepository.save(dealership)).thenReturn(dealership);

        // Act
        Dealership result = dealershipService.saveDealership(dealership);

        // Assert
        assertEquals(dealership, result);
    }

    @Test
    public void testDeleteDealership() {
        // Act
        dealershipService.deleteDealership(1);

        // Assert
        verify(dealershipRepository, times(1)).deleteById(1);
    }
}
