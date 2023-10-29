import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizerViewWorkComponent } from './organizer-view-work.component';

describe('OrganizerViewWorkComponent', () => {
  let component: OrganizerViewWorkComponent;
  let fixture: ComponentFixture<OrganizerViewWorkComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OrganizerViewWorkComponent]
    });
    fixture = TestBed.createComponent(OrganizerViewWorkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
