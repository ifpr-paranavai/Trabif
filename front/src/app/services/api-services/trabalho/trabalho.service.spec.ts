import { TestBed } from '@angular/core/testing';

import { TrabalhoService } from './trabalho.service';

describe('TrabalhoService', () => {
  let service: TrabalhoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TrabalhoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
