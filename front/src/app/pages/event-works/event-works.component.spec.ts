import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventWorksComponent } from './event-works.component';

describe('EventWorksComponent', () => {
  let component: EventWorksComponent;
  let fixture: ComponentFixture<EventWorksComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EventWorksComponent]
    });
    fixture = TestBed.createComponent(EventWorksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
