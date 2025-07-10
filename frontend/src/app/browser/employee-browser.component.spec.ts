import { ComponentFixture, TestBed } from '@angular/core/testing';

import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { EmployeeBrowserComponent } from './employee-browser.component';

describe('EmployeeBrowserComponent', () => {
  let component: EmployeeBrowserComponent;
  let fixture: ComponentFixture<EmployeeBrowserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeeBrowserComponent],
      providers: [provideHttpClient(), provideHttpClientTesting()]
    }).compileComponents();

    fixture = TestBed.createComponent(EmployeeBrowserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
