import { Component } from '@angular/core';
import {AddFormComponent} from "../add-form/add-form.component";
import {CarsModule} from "../cars/cars.module";
import {ChartModule} from "../chart/chart.module";
import {NetworkStatusComponent} from "../network-status/network-status.component";
import {ServerStatusComponent} from "../server-status/server-status.component";
import {CarService} from "../cars/shared/car.service";
import {LoginService} from "../loginPage/shared/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  standalone: true,
    imports: [
        AddFormComponent,
        CarsModule,
        ChartModule,
        NetworkStatusComponent,
        ServerStatusComponent
    ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  isNetworkAvailable: boolean;

  constructor(private carService: CarService, private loginService: LoginService, private router: Router) {
    this.isNetworkAvailable = false;
  }

  ngOnInit() {
    this.carService.checkNetworkStatus().subscribe(
      (isAvailable) => {
        this.isNetworkAvailable = isAvailable;
      }
    );
  }

  logout(){
    this.loginService.logout();
    this.router.navigate(['']);
  }
}
