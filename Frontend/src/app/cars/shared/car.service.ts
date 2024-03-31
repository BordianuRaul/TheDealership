import { Injectable } from '@angular/core';
import { Car } from './car.model'
import {BehaviorSubject, Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";

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
    this.idCount = 0;
    this.cars = [];
    this.http.get<Car[]>(this.baseUrl).subscribe(
      (response: Car[]) => {

        this.carsSubject.next(this.cars);

        response.forEach((car: Car) => {
          this.refreshAdd(car.id, car.model, car.brand, car.year);
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

  refreshAdd(id:number, model:string, brand:string, year:number): void{
    let newCar = new Car(id, model, brand, year);
    this.cars.push(newCar);
    this.emitCars();
  }

  add(model: string, brand: string, year: number): void {

    if (model.trim() == '' || brand.trim() == '') {
      this.errorSubject.next('Model and brand cannot be empty');
      return;
    }
    if (year < 1886) {
      this.errorSubject.next('Year must be 1700 or later');
      return;
    }

    const params = {
      model: model,
      brand: brand,
      year: year
    };

    this.http.post(this.baseUrl, null, { params: params }).subscribe(
      () => {
        this.getAllFromBackend();
      },
      (error) => {
        console.error('Error adding car:', error);
      }
    );
  }

  delete(car: Car): void{
    const deleteUrl = `${this.baseUrl}`;
    this.http.request('delete', deleteUrl, { body: car }).subscribe(
      () => {
        this.getAllFromBackend();
      },
      (error) => {
        console.error('Error deleting car:', error);

      }
    );
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
    const params = {
      model: newModel,
      brand: newBrand,
      year: newYear
    };
    this.http.put(this.baseUrl,car, {params: params}).subscribe(
      () => {
        this.getAllFromBackend();
      },
      (error) => {
        console.error('Error update car:', error);
      }
    );

  }

  getCarById(id: number): Car {
    const params = new HttpParams().set('id', id.toString());
    this.http.get<Car>(`${this.baseUrl}/id`, { params }).subscribe(
      (car: Car) => {
        return car;
      },
      (error) => {
        console.error('Error fetching car:', error);
      }
    );
    return new Car(-1, " ", " ", -1);
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
