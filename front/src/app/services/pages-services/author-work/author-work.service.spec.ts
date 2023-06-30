import { TestBed } from '@angular/core/testing';

import { AuthorWorkService } from './author-work.service';

describe('AuthorWorkService', () => {
  let service: AuthorWorkService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthorWorkService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
