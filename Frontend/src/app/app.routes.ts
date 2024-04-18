import { RouterModule, Routes } from '@angular/router';
import { NgModule } from "@angular/core";
import { CarListComponent } from "./cars/car-list/car-list.component";
import { DealershipComponent } from "./dealership/dealership.component";
import { AppComponent } from "./app.component";
import {HomeComponent} from "./home/home.component";

export const routes: Routes = [
  {path: '', component: HomeComponent},
  { path: 'dealership', component: DealershipComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

