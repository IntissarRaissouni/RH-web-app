import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPayrollComponent } from './user-payroll.component';

describe('UserPayrollComponent', () => {
  let component: UserPayrollComponent;
  let fixture: ComponentFixture<UserPayrollComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserPayrollComponent]
    });
    fixture = TestBed.createComponent(UserPayrollComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
