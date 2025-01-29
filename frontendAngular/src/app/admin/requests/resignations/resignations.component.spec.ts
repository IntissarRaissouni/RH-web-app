import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResignationsComponent } from './resignations.component';

describe('ResignationsComponent', () => {
  let component: ResignationsComponent;
  let fixture: ComponentFixture<ResignationsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResignationsComponent]
    });
    fixture = TestBed.createComponent(ResignationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
