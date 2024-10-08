package com.example.backend.model;

import jakarta.persistence.*;


import java.lang.reflect.Constructor;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    private String brand;
    private int year;

    @ManyToOne
    @JoinColumn(name = "dealership_id")
    private Dealership dealership;

    public Car(String model, String brand, int year, Dealership dealership) throws Exception {
        if(model.equals("") || brand.equals("") || year < 1769)
            throw new Exception("Invalid data for the car");
        if(model.equals("Mustang") && !brand.equals("Ford"))
            throw new Exception("Only Ford makes mustangs!");
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.dealership = dealership;
    }

    public Car() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Dealership getDealership() {
        return dealership;
    }
    @Override
    public String toString() {
        return "id = " + id +
                " model = " + model +
                " brand = " + brand +
                " year = " + year;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Car otherCar = (Car) obj;
        return id == otherCar.id;
    }
}
