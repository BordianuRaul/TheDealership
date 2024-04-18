import {Component, Input, OnInit} from '@angular/core';
import {CarService} from "../cars/shared/car.service";
import {NgIf} from "@angular/common";
import {ServerStatusComponent} from "../server-status/server-status.component";
import {NetworkStatusComponent} from "../network-status/network-status.component";
import {AddFormComponent} from "../add-form/add-form.component";
import {CarsModule} from "../cars/cars.module";
import {ChartModule} from "../chart/chart.module";
import {Dealership} from "./shared/dealership.model";
import {DealershipService} from "./shared/dealership.service";
import {Car} from "../cars/shared/car.model";

@Component({
  selector: 'app-dealership',
  standalone: true,
  imports: [
    NgIf,
    ServerStatusComponent,
    NetworkStatusComponent,
    AddFormComponent,
    CarsModule,
    ChartModule
  ],
  templateUrl: './dealership.component.html',
  styleUrl: './dealership.component.css'
})
export class DealershipComponent{

  @Input() dealership!: Dealership;


  isNetworkAvailable: boolean = false;

  constructor() {
  }

  ngOnInit() {
    this.dealership.getCars().checkNetworkStatus().subscribe(
      (isAvailable) => {
        this.isNetworkAvailable = isAvailable;
      }
    );
  }

}
