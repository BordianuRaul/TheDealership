import {NgModule} from "@angular/core";
import {CarComponent} from "./car/car.component";
import {CarListComponent} from "./car-list/car-list.component";
import {CommonModule} from "@angular/common";
import {CarService} from "./shared/car.service";
import {DeleteButtonModule} from "../deleteButton/delete-button.module";
import {AddButtonModule} from "../addButton/add-button.module";
import {FormsModule} from "@angular/forms";
import {UpdateButtonModule} from "../update-button/update-button.module";
import {SortButtonComponent} from "../sort-button/sort-button.component";


@NgModule({
  declarations: [
    CarComponent,
    CarListComponent
  ],
  imports: [
    CommonModule,
    DeleteButtonModule,
    AddButtonModule,
    FormsModule,
    AddButtonModule,
    UpdateButtonModule,
    SortButtonComponent
  ],
  providers: [
    CarService
  ],
  exports: [
    CarListComponent
  ]
})

export class CarsModule{}
