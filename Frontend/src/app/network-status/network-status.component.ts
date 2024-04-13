import { Component } from '@angular/core';
import {CarService} from "../cars/shared/car.service";

@Component({
  selector: 'app-network-status',
  standalone: true,
  imports: [],
  templateUrl: './network-status.component.html',
  styleUrl: './network-status.component.css'
})
export class NetworkStatusComponent {


  constructor(private carService: CarService) {
  }

}
