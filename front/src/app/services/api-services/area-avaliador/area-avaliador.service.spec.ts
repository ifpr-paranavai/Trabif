import { TestBed } from '@angular/core/testing';

import { AreaAvaliadorService } from './area-avaliador.service';

describe('AreaService', () => {
  let service: AreaAvaliadorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AreaAvaliadorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
