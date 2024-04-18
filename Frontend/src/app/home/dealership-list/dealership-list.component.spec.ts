import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DealershipListComponent } from './dealership-list.component';

describe('DealershipListComponent', () => {
  let component: DealershipListComponent;
  let fixture: ComponentFixture<DealershipListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DealershipListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DealershipListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
