import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {CarsModule} from "./cars/cars.module";
import {AddFormComponent} from "./add-form/add-form.component";
import {ChartComponent} from "./chart/chart.component";
import {ChartModule} from "./chart/chart.module";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CarsModule, AddFormComponent, ChartModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Cars';
}
