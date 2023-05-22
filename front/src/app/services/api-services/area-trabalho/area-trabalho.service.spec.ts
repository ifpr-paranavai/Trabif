import { TestBed } from '@angular/core/testing';

import { AreaTrabalhoService } from './area-trabalho.service';

describe('AreaTrabalhoService', () => {
  let service: AreaTrabalhoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AreaTrabalhoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
