import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EvaluatorAddComponent } from './evaluator-add.component';

describe('EvaluatorAddComponent', () => {
  let component: EvaluatorAddComponent;
  let fixture: ComponentFixture<EvaluatorAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EvaluatorAddComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EvaluatorAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
