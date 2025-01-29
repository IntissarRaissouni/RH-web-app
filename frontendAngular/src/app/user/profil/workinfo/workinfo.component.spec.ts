import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkinfoComponent } from './workinfo.component';

describe('WorkinfoComponent', () => {
  let component: WorkinfoComponent;
  let fixture: ComponentFixture<WorkinfoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WorkinfoComponent]
    });
    fixture = TestBed.createComponent(WorkinfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
