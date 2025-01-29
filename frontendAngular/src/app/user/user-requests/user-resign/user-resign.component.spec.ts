import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserResignComponent } from './user-resign.component';

describe('UserResignComponent', () => {
  let component: UserResignComponent;
  let fixture: ComponentFixture<UserResignComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserResignComponent]
    });
    fixture = TestBed.createComponent(UserResignComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
