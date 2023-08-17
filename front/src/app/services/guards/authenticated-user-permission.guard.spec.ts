import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { authenticatedUserPermissionGuard } from './authenticated-user-permission.guard';

describe('AuthenticatedUserGuard', () => {
  let guard: authenticatedUserPermissionGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(authenticatedUserPermissionGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
