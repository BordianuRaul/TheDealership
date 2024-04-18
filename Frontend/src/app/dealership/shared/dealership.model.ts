import {CarService} from "../../cars/shared/car.service";
import {Car} from "../../cars/shared/car.model";

export class Dealership {
  private id: number;
  name: string;
  private cars: CarService;



  constructor(id: number, name: string, cars: CarService){
    this.id = id;
    this.name=  name;
    this.cars = cars;
  }

  getId() : number{
    return this.id;
  }

  getName() : string{
    return this.name;
  }

  getCars(): CarService{
    return this.cars;
  }

  setId(id : number) {
    this.id = id;
  }

  set(name: string){
    this.name = name;
  }

  setCars(cars: CarService){
    this.cars = cars;
  }



}
