import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeptEmployeesComponent } from './dept-employees.component';

describe('DeptEmployeesComponent', () => {
  let component: DeptEmployeesComponent;
  let fixture: ComponentFixture<DeptEmployeesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DeptEmployeesComponent]
    });
    fixture = TestBed.createComponent(DeptEmployeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
