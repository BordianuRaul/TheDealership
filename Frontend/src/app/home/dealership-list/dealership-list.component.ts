import { Component } from '@angular/core';
import {DealershipService} from "../../dealership/shared/dealership.service";
import {RouterLink} from "@angular/router";
import {Dealership} from "../../dealership/shared/dealership.model";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-dealership-list',
  standalone: true,
  imports: [
    RouterLink,
    NgForOf
  ],
  templateUrl: './dealership-list.component.html',
  styleUrl: './dealership-list.component.css'
})
export class DealershipListComponent {

  dealerships : Dealership[];

  constructor(private dealershipService : DealershipService) {
    this.dealerships = this.dealershipService.getDealerships();
  }

  selectDealership(dealership: Dealership) {

    console.log('Selected Dealership:', dealership);
  }
}
