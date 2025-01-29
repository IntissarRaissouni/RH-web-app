import { TestBed } from '@angular/core/testing';

import { EmployeesDataService } from './employees-data.service';

describe('EmployeesDataService', () => {
  let service: EmployeesDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmployeesDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
