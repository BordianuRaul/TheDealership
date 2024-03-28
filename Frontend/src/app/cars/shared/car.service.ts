import { Injectable } from '@angular/core';
import { Car } from './car.model'
import {BehaviorSubject, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

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
  private baseUrl = 'http://localhost:8080/api/cars';

  constructor(private http: HttpClient) {
    this.idCount = 0;
    this.cars =  [];
    this.getAllFromBackend();
  }

  private getAllFromBackend(): void {
    this.http.get<Car[]>(this.baseUrl).subscribe(
      (response: Car[]) => {
        response.forEach((car: Car) => {
          this.add(car.model, car.brand, car.year);
        });
      },
      (error) => {
        console.error('Error fetching cars from the backend:', error);
        this.errorSubject.next('Error fetching cars from the backend');
      }
    );
  }

  getAll(): Car[] {
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
