import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AreaEvaluatorComponent } from './area-evaluator.component';

describe('AreaEvaluatorComponent', () => {
  let component: AreaEvaluatorComponent;
  let fixture: ComponentFixture<AreaEvaluatorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AreaEvaluatorComponent]
    });
    fixture = TestBed.createComponent(AreaEvaluatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
