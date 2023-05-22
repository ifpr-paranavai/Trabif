import { TestBed } from '@angular/core/testing';

import { TrabalhoAvaliadorService } from './trabalho-avaliador.service';

describe('TrabalhoAvaliadorService', () => {
  let service: TrabalhoAvaliadorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TrabalhoAvaliadorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
