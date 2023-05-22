import { TestBed } from '@angular/core/testing';

import { AutorTrabalhoService } from './autor-trabalho.service';

describe('AutorTrabalhoService', () => {
  let service: AutorTrabalhoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutorTrabalhoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
