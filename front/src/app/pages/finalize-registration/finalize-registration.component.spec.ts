import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FinalizeRegistrationComponent } from './finalize-registration.component';

describe('FinalizeRegistrationComponent', () => {
  let component: FinalizeRegistrationComponent;
  let fixture: ComponentFixture<FinalizeRegistrationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FinalizeRegistrationComponent]
    });
    fixture = TestBed.createComponent(FinalizeRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
