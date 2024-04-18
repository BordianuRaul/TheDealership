package com.example.backend.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Dealership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;

    public Dealership(String name){
        this.name = name;
    }
    public Dealership(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Dealership() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
