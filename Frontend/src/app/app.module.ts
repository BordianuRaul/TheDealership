import { BrowserModule } from '@angular/platform-browser'
import {NgModule} from "@angular/core";
import {CarComponent} from "./cars/car/car.component";
import {AppComponent} from "./app.component";
import {CarListComponent} from "./cars/car-list/car-list.component";
import {FormsModule} from "@angular/forms";
import {CarsModule} from "./cars/cars.module";
import {DeleteButtonModule} from "./deleteButton/delete-button.module";
import {AddButtonModule} from "./addButton/add-button.module";
import {AppRoutingModule} from "./app.routes";
import { GoogleChartsModule } from 'angular-google-charts';
import { HttpClientModule } from '@angular/common/http';
import {ServerStatusComponent} from "./server-status/server-status.component";
import {ServerStatusModule} from "./server-status/server-status.module";
import {DealershipModule} from "./dealership/dealership.module";
import {DealershipListComponent} from "./home/dealership-list/dealership-list.component";
import {DealershipListModule} from "./home/dealership-list/dealership-list.module";




@NgModule({
  declarations: [

  ],
  imports: [
    BrowserModule,
    FormsModule,
    CarsModule,
    DeleteButtonModule,
    AddButtonModule,
    AppRoutingModule,
    GoogleChartsModule,
    BrowserModule,
    HttpClientModule,
    ServerStatusModule,
    DealershipModule,
    DealershipListModule,

  ],

  providers: [],

})

export class AppModule{

}
