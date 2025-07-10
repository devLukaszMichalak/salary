import { ComponentFixture, TestBed } from '@angular/core/testing';

import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { PageService } from '../../page/page.service';
import { EmployeeCriteriaComponent } from './employee-criteria.component';
import { EmployeeCriteriaService } from './employee-criteria.service';

describe('EmployeeCriteriaComponent', () => {
  let component: EmployeeCriteriaComponent;
  let fixture: ComponentFixture<EmployeeCriteriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeeCriteriaComponent],
      providers: [
        EmployeeCriteriaService,
        provideHttpClient(),
        provideHttpClientTesting(),
        PageService
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(EmployeeCriteriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
