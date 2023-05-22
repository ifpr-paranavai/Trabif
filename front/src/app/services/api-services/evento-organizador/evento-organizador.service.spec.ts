import { TestBed } from '@angular/core/testing';

import { EventoOrganizadorService } from './evento-organizador.service';

describe('EventoOrganizadorService', () => {
  let service: EventoOrganizadorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EventoOrganizadorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
