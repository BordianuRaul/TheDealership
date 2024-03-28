import {Component, Output, EventEmitter, Input} from '@angular/core';
import {Car} from "../cars/shared/car.model";

@Component({
  selector: 'app-delete-button',
  templateUrl: './delete-button.component.html',
  styleUrls: ['./delete-button.component.css']
})

export class DeleteButtonComponent{
  @Output() delete: EventEmitter<Car> = new EventEmitter<Car>();
  @Input() car!: Car;

  onDelete(){
    this.delete.emit(this.car);
  }
}
