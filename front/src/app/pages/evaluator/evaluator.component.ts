import { Component } from '@angular/core';
import { PermissaoUsuario } from 'src/app/models/permissao-usuario';
import { PermissaoUsuarioService } from 'src/app/services/api-services/permissao-usuario/permissao-usuario.service';
import { LoginService } from 'src/app/services/pages-services/login/login.service';

@Component({
  selector: 'app-evaluator',
  templateUrl: './evaluator.component.html',
  styleUrls: ['./evaluator.component.scss']
})
export class EvaluatorComponent {

  userPermissions: PermissaoUsuario[] = [];

  constructor(
    private permissaoUsuarioApiService: PermissaoUsuarioService,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.permissaoUsuarioApiService.getAvaliadoresByIdEvento(this.permissaoUsuarioApiService.userPermissionEvent[0].eventoDTO?.id!).subscribe((result) => {
      if (result.content) {
        this.userPermissions = result.content;
      }
    });
  }
}
