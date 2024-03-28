import {RouterModule, Routes} from '@angular/router';
import {NgModule} from "@angular/core";
import {CarListComponent} from "./cars/car-list/car-list.component";


export const routes: Routes = [
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
