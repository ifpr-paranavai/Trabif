import { TestBed } from '@angular/core/testing';

import { OrganizerAddService } from './organizer-add.service';

describe('OrganizerAddService', () => {
  let service: OrganizerAddService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrganizerAddService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
