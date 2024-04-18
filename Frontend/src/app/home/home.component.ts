import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {DealershipListComponent} from "./dealership-list/dealership-list.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    RouterLink,
    DealershipListComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
