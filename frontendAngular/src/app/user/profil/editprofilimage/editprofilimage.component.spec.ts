import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditprofilimageComponent } from './editprofilimage.component';

describe('EditprofilimageComponent', () => {
  let component: EditprofilimageComponent;
  let fixture: ComponentFixture<EditprofilimageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditprofilimageComponent]
    });
    fixture = TestBed.createComponent(EditprofilimageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
