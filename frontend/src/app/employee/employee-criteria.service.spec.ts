import { TestBed } from '@angular/core/testing';

import { EmployeeCriteriaService } from './employee-criteria.service';

describe('EmployeeCriteriaService', () => {
  let service: EmployeeCriteriaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmployeeCriteriaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
