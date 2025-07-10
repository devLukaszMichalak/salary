import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeResultTableComponent } from './employee-result-table.component';

describe('EmployeeResultTableComponent', () => {
  let component: EmployeeResultTableComponent;
  let fixture: ComponentFixture<EmployeeResultTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeeResultTableComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(EmployeeResultTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
