import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AreaUpdateComponent } from './area-update.component';

describe('AreaUpdateComponent', () => {
  let component: AreaUpdateComponent;
  let fixture: ComponentFixture<AreaUpdateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AreaUpdateComponent]
    });
    fixture = TestBed.createComponent(AreaUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
