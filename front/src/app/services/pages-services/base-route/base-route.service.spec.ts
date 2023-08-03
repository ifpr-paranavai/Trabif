import { TestBed } from '@angular/core/testing';

import { BaseRouteService } from './base-route.service';

describe('BaseRouteService', () => {
  let service: BaseRouteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BaseRouteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
