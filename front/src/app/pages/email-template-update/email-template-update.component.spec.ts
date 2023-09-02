import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmailTemplateUpdateComponent } from './email-template-update.component';

describe('EmailTemplateUpdateComponent', () => {
  let component: EmailTemplateUpdateComponent;
  let fixture: ComponentFixture<EmailTemplateUpdateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EmailTemplateUpdateComponent]
    });
    fixture = TestBed.createComponent(EmailTemplateUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
