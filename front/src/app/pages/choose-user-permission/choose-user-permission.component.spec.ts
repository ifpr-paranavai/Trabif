import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChooseUserPermissionComponent } from './choose-user-permission.component';

describe('ChooseUserPermissionComponent', () => {
  let component: ChooseUserPermissionComponent;
  let fixture: ComponentFixture<ChooseUserPermissionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChooseUserPermissionComponent]
    });
    fixture = TestBed.createComponent(ChooseUserPermissionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
