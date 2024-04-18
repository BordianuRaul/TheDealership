import {NgModule} from "@angular/core";
import {CarComponent} from "../cars/car/car.component";
import {CarListComponent} from "../cars/car-list/car-list.component";
import {CommonModule} from "@angular/common";
import {CarService} from "../cars/shared/car.service";
import {HomeComponent} from "./home.component";
import {DealershipService} from "../dealership/shared/dealership.service";



@NgModule({
  declarations: [

  ],
  imports: [
    CommonModule,
    HomeComponent
  ],
  providers: [
    DealershipService
  ],
  exports: [
    HomeComponent
  ]
})

export class HomeModule{}
