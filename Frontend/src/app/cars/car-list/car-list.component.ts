import {Component, ElementRef, EventEmitter, OnDestroy, OnInit, Output, ViewChild} from '@angular/core';
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
  private carsSubscription!: Subscription;

  @Output() carSelected: EventEmitter<Car> = new EventEmitter<Car>();
  @ViewChild('carListContainer', { static: true }) carList!: ElementRef;

  constructor(private carService: CarService) {
    this.cars = this.carService.getAll();
  }

  ngOnInit() {
    this.carsSubscription = this.carService.cars$.subscribe(cars => {
      this.cars = this.carService.getAll();
    });
    this.carList.nativeElement.addEventListener('scroll', this.onScroll.bind(this));
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
    this.carList.nativeElement.removeEventListener('scroll', this.onScroll);
  }
  selectCar(car: Car) {
    this.carService.selectCar(car);
  }

  sortAscending(){
    this.carService.sortAscending();
    this.refreshCars();
  }

  nextPage() {
    this.carService.incrementCurrentPageNumber();
  }

  loadMoreCars(): void{

    this.nextPage();
    this.cars.concat(this.carService.getCarsFromCurrentPage());

  }

  onScroll() {

    if (this.carList.nativeElement.scrollTop + this.carList.nativeElement.clientHeight >= this.carList.nativeElement.scrollHeight - 1) {
      this.loadMoreCars();
    }
  }


}
