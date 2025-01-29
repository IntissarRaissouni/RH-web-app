import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyResignsComponent } from './my-resigns.component';

describe('MyResignsComponent', () => {
  let component: MyResignsComponent;
  let fixture: ComponentFixture<MyResignsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MyResignsComponent]
    });
    fixture = TestBed.createComponent(MyResignsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
