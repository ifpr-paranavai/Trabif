import { TestBed } from '@angular/core/testing';

import { ChooseUserPermissionService } from './choose-user-permission.service';

describe('ChooseUserPermissionService', () => {
  let service: ChooseUserPermissionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChooseUserPermissionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
