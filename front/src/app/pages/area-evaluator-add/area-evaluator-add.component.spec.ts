import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AreaEvaluatorAddComponent } from './area-evaluator-add.component';

describe('AreaEvaluatorAddComponent', () => {
  let component: AreaEvaluatorAddComponent;
  let fixture: ComponentFixture<AreaEvaluatorAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AreaEvaluatorAddComponent]
    });
    fixture = TestBed.createComponent(AreaEvaluatorAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
