import { ToastService } from 'src/app/services/pages-services/toast/toast.service';
import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { Evento } from 'src/app/models/evento';
import { PermissaoUsuario } from 'src/app/models/permissao-usuario';
import { Usuario } from 'src/app/models/usuario';
import { PermissaoUsuarioService } from 'src/app/services/api-services/permissao-usuario/permissao-usuario.service';
import { LoginService } from 'src/app/services/pages-services/login/login.service';
import { MainService } from 'src/app/services/pages-services/main/main.service';


@Component({
  selector: 'app-evaluator-add',
  templateUrl: './evaluator-add.component.html',
  styleUrls: ['./evaluator-add.component.scss']
})
export class EvaluatorAddComponent {
  permissaoUsuario: any = new PermissaoUsuario();
  sendObj: any = new Object();
  loading = false;

  constructor(
    private permissaoUsuarioApiService: PermissaoUsuarioService,
    public mainService: MainService,
    private loginService: LoginService,
    private toastService: ToastService
  ) {}

  addEvaluator(): void {
    if (this.sendObj.email) {
      this.loading = true;
      this.permissaoUsuario.usuario = new Usuario();
      this.permissaoUsuario.usuario.email = this.sendObj.email;
      this.permissaoUsuario.evento = new Evento();
      this.permissaoUsuario.evento.id = this.permissaoUsuarioApiService.userPermissionEvent[0].eventoDTO?.id;
      this.permissaoUsuarioApiService.postEvaluator(this.permissaoUsuario).subscribe((result) => {
        if (result) {
          this.mainService.goToMain();
          this.toastService.showSuccess("Avaliador adicionado com sucesso!");
        }
        this.loading = false;
      });
    }
  }


}
