import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizerAddComponent } from './organizer-add.component';

describe('OrganizerAddComponent', () => {
  let component: OrganizerAddComponent;
  let fixture: ComponentFixture<OrganizerAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OrganizerAddComponent]
    });
    fixture = TestBed.createComponent(OrganizerAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
