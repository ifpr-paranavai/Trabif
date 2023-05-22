import { TestBed } from '@angular/core/testing';

import { PermissaoUsuarioService } from './permissao-usuario.service';

describe('PermissaoUsuarioService', () => {
  let service: PermissaoUsuarioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PermissaoUsuarioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
