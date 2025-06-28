import { ComponentFixture, TestBed } from '@angular/core/testing';

import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { EmployeeCriteriaService } from '../../employee/employee-criteria.service';
import { PageService } from '../../page/page.service';
import { CriteriaComponent } from './criteria.component';

describe('CriteriaComponent', () => {
  let component: CriteriaComponent;
  let fixture: ComponentFixture<CriteriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CriteriaComponent],
      providers: [
        EmployeeCriteriaService,
        provideHttpClient(),
        provideHttpClientTesting(),
        PageService
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CriteriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
