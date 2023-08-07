
import { Router } from '@angular/router';
import { MainService } from './../../services/pages-services/main/main.service';
import { Component } from '@angular/core';
import { PermissaoUsuario } from 'src/app/models/permissao-usuario';
import { PermissaoUsuarioService } from 'src/app/services/api-services/permissao-usuario/permissao-usuario.service';
import { LoginService } from 'src/app/services/pages-services/login/login.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';

@Component({
  selector: 'app-evaluator',
  templateUrl: './evaluator.component.html',
  styleUrls: ['./evaluator.component.scss']
})
export class EvaluatorComponent {

  userPermissions: PermissaoUsuario[] = [];

  constructor(
    private permissaoUsuarioApiService: PermissaoUsuarioService,
    private loginService: LoginService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.getUserPermissions();
  }

  deleteUserPermission(id: number): void {
    this.permissaoUsuarioApiService.delete(id).subscribe((result) => {
      if (!result) {
        this.toastService.showSuccess("Avaliador removido com sucesso!");
        this.getUserPermissions();
      }
    });
  }

  getUserPermissions(): void {
    this.permissaoUsuarioApiService.getAvaliadoresByIdEvento(this.permissaoUsuarioApiService.userPermissionEvent[0].eventoDTO?.id!).subscribe((result) => {
      if (result.content) {
        this.userPermissions = result.content;
      }
    });
  }
}
