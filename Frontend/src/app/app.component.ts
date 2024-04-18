import {Component, OnInit} from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import {CarsModule} from "./cars/cars.module";
import {AddFormComponent} from "./add-form/add-form.component";
import {ChartComponent} from "./chart/chart.component";
import {ChartModule} from "./chart/chart.module";
import {ServerStatusComponent} from "./server-status/server-status.component";
import {NetworkStatusComponent} from "./network-status/network-status.component";
import {CarService} from "./cars/shared/car.service";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CarsModule, AddFormComponent, ChartModule, ServerStatusComponent, NetworkStatusComponent, NgIf, RouterLink],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent{

}
