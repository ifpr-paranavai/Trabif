import { TestBed } from '@angular/core/testing';

import { ResultadoSubmissaoService } from './resultado-submissao.service';

describe('ResultadoSubmissaoService', () => {
  let service: ResultadoSubmissaoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResultadoSubmissaoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
