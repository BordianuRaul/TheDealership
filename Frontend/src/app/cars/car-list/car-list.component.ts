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
  carsPerPage: number = 5;
  totalPages!: number;
  private carsSubscription!: Subscription;

  @Output() carSelected: EventEmitter<Car> = new EventEmitter<Car>();

  constructor(private carService: CarService) {
    const startIndex = (this.currentPage - 1) * this.carsPerPage;
    const endIndex = startIndex + this.carsPerPage;
    this.cars = this.carService.getAllRange(startIndex, endIndex);
    this.calculateTotalPages();
  }

  ngOnInit() {
    this.carsSubscription = this.carService.cars$.subscribe(cars => {
      const startIndex = (this.currentPage - 1) * this.carsPerPage;
      const endIndex = startIndex + this.carsPerPage;
      this.cars = this.carService.getAllRange(startIndex, endIndex);
      this.calculateTotalPages();
    });
  }
  refreshCars(){
    const startIndex = (this.currentPage - 1) * this.carsPerPage;
    const endIndex = startIndex + this.carsPerPage;
    this.cars = this.carService.getAllRange(startIndex, endIndex);
    this.calculateTotalPages();
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
  calculateTotalPages() {
    const totalCars = this.carService.size();
    this.totalPages = Math.ceil(totalCars / this.carsPerPage);
  }

  goToPage(pageNumber: number) {
    if (pageNumber >= 1 && pageNumber <= this.totalPages) {
      this.currentPage = pageNumber;
      this.refreshCars();
    }
  }

  nextPage() {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.refreshCars();
    }
  }

  previousPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.refreshCars();
    }
  }


}
