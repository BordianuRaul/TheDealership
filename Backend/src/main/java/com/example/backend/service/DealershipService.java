package com.example.backend.service;

import com.example.backend.model.Dealership;
import com.example.backend.repository.DealershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

public class DealershipService {

    private final DealershipRepository repository;
    
    @Autowired
    public DealershipService(DealershipRepository repository) {
        this.repository = repository;
    }

    public List<Dealership> getAllDealerships() {
        return (List<Dealership>) repository.findAll();
    }

    public Optional<Dealership> getDealershipById(int id) {
        return repository.findById(id);
    }

    public Dealership saveDealership(Dealership dealership) {
        return repository.save(dealership);
    }

    public void deleteDealership(int id) {
        repository.deleteById(id);
    }

}
