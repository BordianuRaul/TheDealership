
export class Car{

  id: number;
  model: string;
  brand: string;
  year: number;


  constructor(id:number, model:string, brand:string, year:number) {
    this.id = id;
    this.model = model;
    this.brand = brand;
    this.year = year;

  }

  toString(): string{

    return `${this.brand} ${this.model} ${this.year}`;

}

  getModel(): string{
    return this.model;
  }

  getBrand(): string{
    return this.brand;
  }

  getYear(): number{
    return this.year;
  }

  setModel(model: string): void {
    this.model = model;
  }

  setBrand(brand: string): void {
    this.brand = brand;
  }

  setYear(year: number): void {
    this.year = year;
  }



}
