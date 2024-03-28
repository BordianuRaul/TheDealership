import { Injectable } from '@angular/core';
import { Car } from './car.model'
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})

export class CarService{

  private carsSubject: BehaviorSubject<Car[]> = new BehaviorSubject<Car[]>([]);
  cars$ = this.carsSubject.asObservable();

  private selectedCarSubject: BehaviorSubject<Car | null> = new BehaviorSubject<Car | null>(null);
  selectedCar$: Observable<Car | null> = this.selectedCarSubject.asObservable();

  private errorSubject: BehaviorSubject<string> = new BehaviorSubject<string>('');
  error$: Observable<string> = this.errorSubject.asObservable();

  private cars: Car[];
  idCount: number;
  selectedCar: Car | null = null;

  constructor() {
    this.cars = [];
    this.idCount = 1;

    this.add('Camry', 'Toyota', 2020);
    this.add('Civic', 'Honda', 2019);
    this.add('Mustang', 'Ford', 2021);
    this.add('Model 3', 'Tesla', 2022);
    this.add('Corvette', 'Chevrolet', 2023);
    this.add('Accord', 'Honda', 2020);
    this.add('F-150', 'Ford', 2021);
    this.add('Camry', 'Toyota', 2022);
    this.add('Civic', 'Honda', 2023);
    this.add('Model S', 'Tesla', 2020);
    this.add('Silverado', 'Chevrolet', 2021);
    this.add('RAV4', 'Toyota', 2022);
    this.add('CR-V', 'Honda', 2023);
    this.add('Mustang', 'Ford', 2020);
    this.add('Model Y', 'Tesla', 2021);
    this.add('Equinox', 'Chevrolet', 2022);
    this.add('Highlander', 'Toyota', 2023);
    this.add('Civic', 'Honda', 2020);
    this.add('F-150', 'Ford', 2021);
    this.add('Model 3', 'Tesla', 2022);
    this.add('3 Series', 'BMW', 2023);
    this.add('A4', 'Audi', 2022);
    this.add('C-Class', 'Mercedes-Benz', 2021);
    this.add('X5', 'BMW', 2020);
    this.add('A6', 'Audi', 2019);
    this.add('E-Class', 'Mercedes-Benz', 2018);
    this.add('M3', 'BMW', 2017);
    this.add('Q5', 'Audi', 2016);
    this.add('GLC', 'Mercedes-Benz', 2015);
    this.add('X3', 'BMW', 2014);
  }

  getAll() : Car[]{
    return this.cars;
  }

  add(model:string, brand:string, year:number): void{

    if (model.trim() == '' || brand.trim() == '') {
      this.errorSubject.next('Model and brand cannot be empty');
      return;
    }
    if (year < 1886) {
      this.errorSubject.next('Year must be 1700 or later');
      return;
    }

    let newCar = new Car(this.idCount, model, brand, year);
    this.incrementIdCount();
    this.cars.push(newCar);
    this.emitCars();
  }

  delete(car:Car) : void{

    for(let i = 0; i <= this.cars.length; i++)
    {
      if(this.cars[i].id == car.id)
      {
        this.cars.splice(i, 1);
        this.emitCars();
        return;
      }
    }

  }

  update(car: Car , newModel: string, newBrand: string, newYear: number){

    if (newModel.trim() == '' || newBrand.trim() == '') {
      this.errorSubject.next('Model and brand cannot be empty');
      return;
    }
    if (newYear < 1886) {
      this.errorSubject.next('Year must be 1700 or later');
      return;
    }

    for(let i = 0; i <= this.cars.length; i++)
    {
      if(this.cars[i].id == car.id)
      {
        this.cars[i].model = newModel;
        this.cars[i].brand = newBrand;
        this.cars[i].year = newYear;
        this.emitCars();
        return;
      }
    }

  }

  private incrementIdCount():void{
    this.idCount++;
  }

  private emitCars(): void {
    this.carsSubject.next([...this.cars]);
  }

  selectCar(car: Car) {
    this.selectedCarSubject.next(car);
  }

  clearSelectedCar() {
    this.selectedCarSubject.next(null);
  }
  size(): number{
    return this.cars.length;
  }
  sortAscending() {
    this.cars.sort((car1, car2) => {
      if (car1.year < car2.year) {
        return -1;
      } else if (car1.year > car2.year) {
        return 1;
      } else {
        return 0;
      }
    });
  }
  getAllRange(startIndex: number, endIndex: number): Car[] {
    return this.cars.slice(startIndex, endIndex);
  }

}
