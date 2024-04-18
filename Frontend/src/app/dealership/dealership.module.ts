import {NgModule} from "@angular/core";
import {CarComponent} from "../cars/car/car.component";
import {CarListComponent} from "../cars/car-list/car-list.component";
import {CommonModule} from "@angular/common";
import {CarService} from "../cars/shared/car.service";
import {DealershipComponent} from "./dealership.component";
import {DealershipService} from "./shared/dealership.service";
import {HttpClientModule} from "@angular/common/http";


@NgModule({
  declarations: [

  ],
  imports: [
    CommonModule,
    DealershipComponent
  ],
  providers: [
    DealershipService,
    HttpClientModule

  ],
  exports: [
    DealershipComponent
  ]
})

export class DealershipModule{}
