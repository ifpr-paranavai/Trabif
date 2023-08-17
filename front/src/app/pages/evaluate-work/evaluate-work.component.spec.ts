import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EvaluateWorkComponent } from './evaluate-work.component';

describe('EvaluateWorkComponent', () => {
  let component: EvaluateWorkComponent;
  let fixture: ComponentFixture<EvaluateWorkComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EvaluateWorkComponent]
    });
    fixture = TestBed.createComponent(EvaluateWorkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
