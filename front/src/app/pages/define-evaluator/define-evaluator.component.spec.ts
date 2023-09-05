import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefineEvaluatorComponent } from './define-evaluator.component';

describe('DefineEvaluatorComponent', () => {
  let component: DefineEvaluatorComponent;
  let fixture: ComponentFixture<DefineEvaluatorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DefineEvaluatorComponent]
    });
    fixture = TestBed.createComponent(DefineEvaluatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
