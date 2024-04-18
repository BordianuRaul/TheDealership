import {DealershipComponent} from "../dealership.component";
import {CarService} from "../../cars/shared/car.service";
import {HttpClient, HttpHandler} from "@angular/common/http";
import {Dealership} from "./dealership.model";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class DealershipService{

  private dealerships: Dealership[];

  constructor(private httpClient: HttpClient) {
    this.dealerships = [];
    let mockCars1 = new CarService(httpClient);
    let mockCars2 = new CarService(httpClient);
    this.addDealership(1, 'ABC Motors', mockCars1);
    this.addDealership(2, 'XYZ Autos', mockCars2);
  }


  getDealerships(): Dealership[] {
    return this.dealerships;
  }

  addDealership(id: number, name: string, cars: CarService): void {
    const newDealership = new Dealership(id, name, cars);
    this.dealerships.push(newDealership);
  }
}
