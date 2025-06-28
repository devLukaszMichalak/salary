import { TestBed } from '@angular/core/testing';

import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { PageService } from '../page/page.service';
import { EmployeeCriteriaService } from './employee-criteria.service';

describe('EmployeeCriteriaService', () => {
  let service: EmployeeCriteriaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        EmployeeCriteriaService,
        PageService,
        provideHttpClient(),
        provideHttpClientTesting()
      ]
    });
    service = TestBed.inject(EmployeeCriteriaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
