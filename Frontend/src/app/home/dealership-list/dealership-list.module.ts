import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { DealershipListComponent } from "./dealership-list.component";
import { DealershipService } from "../../dealership/shared/dealership.service";

@NgModule({
  declarations: [

  ],
  imports: [
    CommonModule,
    DealershipListComponent
  ],
  providers: [
    DealershipService

  ],
  exports: [
    DealershipListComponent
  ]
})
export class DealershipListModule {}
