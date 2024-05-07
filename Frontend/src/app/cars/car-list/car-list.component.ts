import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import { Car } from '../shared/car.model';
import { CarService } from '../shared/car.service';
import {Subscription} from "rxjs";

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})

export class CarListComponent implements OnInit, OnDestroy{
  cars: Car[];
  currentPage: number = 1;
  carsPerPage: number = 50;
  totalPages!: number;
  private carsSubscription!: Subscription;

  @Output() carSelected: EventEmitter<Car> = new EventEmitter<Car>();

  constructor(private carService: CarService) {
    this.cars = this.carService.getAll();
  }

  ngOnInit() {
    this.carsSubscription = this.carService.cars$.subscribe(cars => {
      const startIndex = (this.currentPage - 1) * this.carsPerPage;
      const endIndex = startIndex + this.carsPerPage;
      this.cars = this.carService.getAllRange(startIndex, endIndex);
    });
  }
  refreshCars(){
    this.cars = this.carService.getAll();
  }
  onDelete(car: Car) {
    this.carService.delete(car);
    this.refreshCars();
  }
  ngOnDestroy() {
    this.carsSubscription.unsubscribe();
  }
  selectCar(car: Car) {
    this.carService.selectCar(car);
  }

  sortAscending(){
    this.carService.sortAscending();
    this.refreshCars();
  }


  goToPage(pageNumber: number) {
    if (pageNumber >= 1 && pageNumber <= this.totalPages) {
      this.currentPage = pageNumber;
      this.refreshCars();
    }
  }

  nextPage() {
    this.carService.incrementCurrentPageNumber();
    this.refreshCars();
  }

  previousPage() {
    this.carService.decrementCurrentPageNumber();
    this.refreshCars();
  }

}
