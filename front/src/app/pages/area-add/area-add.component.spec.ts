import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AreaAddComponent } from './area-add.component';

describe('AreaAddComponent', () => {
  let component: AreaAddComponent;
  let fixture: ComponentFixture<AreaAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AreaAddComponent]
    });
    fixture = TestBed.createComponent(AreaAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
