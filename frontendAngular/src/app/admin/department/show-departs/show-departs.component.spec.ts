import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowDepartsComponent } from './show-departs.component';

describe('ShowDepartsComponent', () => {
  let component: ShowDepartsComponent;
  let fixture: ComponentFixture<ShowDepartsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShowDepartsComponent]
    });
    fixture = TestBed.createComponent(ShowDepartsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
