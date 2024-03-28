import {Component, EventEmitter, OnDestroy, Output} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {AddButtonModule} from "../addButton/add-button.module";
import {CarService} from "../cars/shared/car.service";
import {Car} from "../cars/shared/car.model";
import {Subscription} from "rxjs";
import {UpdateButtonModule} from "../update-button/update-button.module";

@Component({
  selector: 'app-add-form',
  standalone: true,
  imports: [
    FormsModule,
    AddButtonModule,
    UpdateButtonModule
  ],
  templateUrl: './add-form.component.html',
  styleUrl: './add-form.component.css'
})
export class AddFormComponent implements OnDestroy{

  model: string;
  brand: string;
  year: number;
  selectedCar!: Car ;
  private selectedCarSubscription: Subscription;

  @Output() formSubmitted: EventEmitter<void> = new EventEmitter<void>();

  constructor(private carService: CarService) {
    this.model = '';
    this.brand = '';
    this.year = -1;
    this.selectedCarSubscription = this.carService.selectedCar$.subscribe(car => {
      if (car) {
        this.updateFormData(car);
        this.selectedCar = car;
      }
    });

  }
  submitForm() {
    this.carService.add(this.model, this.brand, this.year);
    this.formSubmitted.emit();
  }

  submitUpdate(){
    this.carService.update(this.selectedCar, this.model, this.brand, this.year);
  }
  ngOnDestroy() {
    this.selectedCarSubscription.unsubscribe();
  }
  private updateFormData(car: Car): void {
    this.model = car.model;
    this.brand = car.brand;
    this.year = car.year;
  }

  size():number{
    return this.carService.size();
  }
}
