import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EvaluateWorkListComponent } from './evaluate-work-list.component';

describe('EvaluateWorkListComponent', () => {
  let component: EvaluateWorkListComponent;
  let fixture: ComponentFixture<EvaluateWorkListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EvaluateWorkListComponent]
    });
    fixture = TestBed.createComponent(EvaluateWorkListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
