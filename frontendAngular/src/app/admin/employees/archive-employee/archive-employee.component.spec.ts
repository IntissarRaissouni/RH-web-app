import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArchiveEmployeeComponent } from './archive-employee.component';

describe('ArchiveEmployeeComponent', () => {
  let component: ArchiveEmployeeComponent;
  let fixture: ComponentFixture<ArchiveEmployeeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ArchiveEmployeeComponent]
    });
    fixture = TestBed.createComponent(ArchiveEmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
