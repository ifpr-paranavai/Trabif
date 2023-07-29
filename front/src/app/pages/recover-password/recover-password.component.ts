import { MainService } from './../../services/pages-services/main/main.service';
import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/models/usuario';
import { UsuarioService } from 'src/app/services/api-services/usuario/usuario.service';
import { LoginService } from 'src/app/services/pages-services/login/login.service';

@Component({
  selector: 'app-recover-password',
  templateUrl: './recover-password.component.html',
  styleUrls: ['./recover-password.component.scss'],
})
export class RecoverPasswordComponent {
  constructor(
    public mainService: MainService,
    public loginService: LoginService,
    public usuarioService: UsuarioService
  ) {}

  usuario: Usuario = new Usuario();
  confirmSenha: string = '';
  divRecover = true
  loading = false;

  recoverPassword(): void {
    this.loading = true;
    this.usuarioService.recoverPassword(this.usuario).subscribe({
      next: (result) => {
        this.loading = false
        if (result) {
          alert(result.resposta);
          this.divRecover = !this.divRecover
        } else {
          alert('Erro ao gerar código de recuperação de senha');
        }
      },
      error: (error) => {
        this.loading = false
        if (error.status == 400) {
          alert('E-mail ou senha inválidos');
        } else {
          alert('Ocorreu um erro inesperado');
        }
      },
    });
  }

  changePassword(): void {
    if (this.usuario.senha == this.confirmSenha) {
      this.loading = true;
      this.usuarioService.changePassword(this.usuario).subscribe({
        next: (result) => {
          if (result) {
            alert(result.resposta);
            this.loginService.goToLogin();
          } else {
            alert('Erro ao gerar código de recuperação de senha');
          }
          this.loading = false;
        },
        error: (error) => {
          if (error.status == 400) {
            alert('E-mail ou senha inválidos');
          } else {
            alert('Ocorreu um erro inesperado');
          }
          this.loading = false;
        },
      });
    } else {
      alert('As senhas devem ser iguais');
    }
  }
}
