import { TestBed } from '@angular/core/testing';

import { EventoEmailTemplateService } from './evento-email-template.service';

describe('EventoEmailTemplateService', () => {
  let service: EventoEmailTemplateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EventoEmailTemplateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
