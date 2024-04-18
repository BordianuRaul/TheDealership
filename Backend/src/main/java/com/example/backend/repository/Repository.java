package com.example.backend.repository;

import com.example.backend.model.Car;
import com.example.backend.model.Dealership;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Repository {
    private List<Dealership> dealerships;

    public Repository(){
        this.dealerships  = new ArrayList<>();
        Dealership firstDealership = new Dealership(1, "MotivateAuto", new ArrayList<>());
        this.dealerships.add(firstDealership);
    };

    public void addCar(Car car){
        this.dealerships.get(0).getCars().add(car);
    }

    public void updateCar(Car oldCar, String model, String brand, int year){
        for(Car car: this.dealerships.get(0).getCars()){
            if(car.equals(oldCar)){
                car.setModel(model);
                car.setBrand(brand);
                car.setYear(year);
                break;
            }
        }
    }

    public void deleteCar(Car carToDelete) {
        Iterator<Car> iterator = this.dealerships.get(0).getCars().iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            if (car.equals(carToDelete)) {
                iterator.remove();
                break;
            }
        }
    }

    public List<Car> getAllCars(){
        return this.dealerships.get(0).getCars();
    }

    public Car getCarByID(Integer id) throws Exception {
        for (Car car : this.dealerships.get(0).getCars()) {
            if (car.getId() == id) {
                return car;
            }
        }

        return new Car(-1, "", "", -1);
    }
}
