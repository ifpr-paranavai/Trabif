import { MainService } from './../../services/pages-services/main/main.service';
import { Component } from '@angular/core';
import { Usuario } from 'src/app/models/usuario';
import { UsuarioService } from 'src/app/services/api-services/usuario/usuario.service';
import { LoginService } from 'src/app/services/pages-services/login/login.service';

@Component({
  selector: 'app-recover-password',
  templateUrl: './recover-password.component.html',
  styleUrls: ['./recover-password.component.scss']
})
export class RecoverPasswordComponent {
  constructor(
    public mainService: MainService,
    public loginService: LoginService,
    public usuarioService: UsuarioService
  ) {}

  usuario: Usuario = new Usuario();
  confirmSenha: string = '';
  recoverPassword(): void {
    this.usuarioService.recoverPassword(this.usuario).subscribe({
      next: (result) => {
        if (result) {
          // this.usuarioService.loggedUser = result.content;
          alert(result);
          let divRcover = document.getElementById("div-recover-password");
          let divChange = document.getElementById("div-change-password");
          divRcover!.style.display = 'block';
          divChange!.style.display = 'none';
        } else {
          alert('Erro ao gerar código de recuperação de senha');
        }
      },
      error: (error) => {
        if(error.status == 400) {
          alert("E-mail ou senha inválidos");
        } else {
          alert("Ocorreu um erro inesperado");
        }
      }
    });
  }

  changePassword(): void {
    if (this.usuario.senha == this.confirmSenha) {
      this.usuarioService.changePassword(this.usuario).subscribe({
        next: (result) => {
          if (result) {
            alert(result);
            this.mainService.goToMain();
          } else {
            alert('Erro ao gerar código de recuperação de senha');
          }
        },
        error: (error) => {
          if(error.status == 400) {
            alert("E-mail ou senha inválidos");
          } else {
            alert("Ocorreu um erro inesperado");
          }
        }
      });
    } else {
      alert('As senhas devem ser iguais');
    }
  }
}
