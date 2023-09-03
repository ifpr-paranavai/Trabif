import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizerWorkComponent } from './organizer-work.component';

describe('OrganizerWorkComponent', () => {
  let component: OrganizerWorkComponent;
  let fixture: ComponentFixture<OrganizerWorkComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OrganizerWorkComponent]
    });
    fixture = TestBed.createComponent(OrganizerWorkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
