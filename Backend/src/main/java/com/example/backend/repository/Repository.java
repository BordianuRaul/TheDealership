package com.example.backend.repository;

import com.example.backend.model.Car;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Repository {
    private List<Car> cars;

    public Repository(){
        this.cars = new ArrayList<>();
    };

    public void add(Car car){
        this.cars.add(car);
    }

    public void update(Car oldCar,String model, String brand, int year){
        for(Car car: this.cars){
            if(car.equals(oldCar)){
                car.setModel(model);
                car.setBrand(brand);
                car.setYear(year);
                break;
            }
        }
    }

    public void delete(Car carToDelete) {
        Iterator<Car> iterator = this.cars.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            if (car.equals(carToDelete)) {
                iterator.remove();
                break;
            }
        }
    }

    public List<Car> getAll(){
        return this.cars;
    }

    public Car getCarByID(Integer id) throws Exception {
        for (Car car : this.cars) {
            if (car.getId() == id) {
                return car;
            }
        }

        return new Car(-1, "", "", -1);
    }
}
