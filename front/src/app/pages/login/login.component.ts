import { RecoverPasswordService } from './../../services/pages-services/recover-password/recover-password.service';
import { RegisterService } from './../../services/pages-services/register/register.service';
import { UsuarioService } from './../../services/api-services/usuario/usuario.service';
import { Usuario } from './../../models/usuario';
import { Component } from '@angular/core';
import { HeaderComponent } from 'src/app/components/header/header.component';
import { EventService } from 'src/app/services/pages-services/event/event.service';
import { MainService } from 'src/app/services/pages-services/main/main.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  constructor(
    public mainService: MainService,
    public eventService: EventService,
    public registerService: RegisterService,
    public recoverPasswordService: RecoverPasswordService,
    public usuarioService: UsuarioService,
    private toastService: ToastService
  ) {}

  usuario: Usuario = new Usuario();
  login(): void {
    this.usuarioService.login(this.usuario).subscribe({
      next: (result) => {
        if (result.usuario && result.token) {
          localStorage.setItem('token', result.token);
          localStorage.setItem('usuario', JSON.stringify(result.usuario));
          this.eventService.goToEvent();
        } else {
          this.toastService.showWarn("Usuário ou senha inválidos");
        }
      },
      error: (error) => {
        if(error.error.message) {
          this.toastService.showError(error.error.message);
        } else {
          this.toastService.showError("Ocorreu um erro inesperado");
        }
      }
    });
  }
}
