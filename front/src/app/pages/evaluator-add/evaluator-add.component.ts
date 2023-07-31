import { Component } from '@angular/core';
import { PermissaoUsuario } from 'src/app/models/permissao-usuario';
import { PermissaoUsuarioService } from 'src/app/services/api-services/permissao-usuario/permissao-usuario.service';
import { LoginService } from 'src/app/services/pages-services/login/login.service';
import { MainService } from 'src/app/services/pages-services/main/main.service';

@Component({
  selector: 'app-evaluator-add',
  templateUrl: './evaluator-add.component.html',
  styleUrls: ['./evaluator-add.component.scss']
})
export class EvaluatorAddComponent {
  permissaoUsuario: PermissaoUsuario = new PermissaoUsuario();
  sendObj: any = new Object();

  constructor(
    private permissaoUsuarioApiService: PermissaoUsuarioService,
    private mainService: MainService,
    private loginService: LoginService
  ) {}

  addEvaluator(): void {
    if (this.sendObj.email) {
      // this.permissaoUsuarioApiService.postEvent(this.loginService.getLoggedUserId!, this.evento).subscribe(() => {
      //   alert("Evento criado com sucesso!");
      //   this.mainService.goToMain();
      // });
    }
  }
}
