import { Component } from '@angular/core';
import { Usuario } from 'src/app/models/usuario';
import { UsuarioService } from 'src/app/services/api-services/usuario/usuario.service';
import { EventService } from 'src/app/services/pages-services/event/event.service';
import { LoginService } from 'src/app/services/pages-services/login/login.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  constructor(
    public loginService: LoginService,
    public usuarioService: UsuarioService
  ) {}

  usuario: Usuario = new Usuario();

  saveUsuario(): void {
    this.usuarioService.post(this.usuario).subscribe({
      next: (result) => {
        if (result.content) {
          this.loginService.goToLogin();
        }
      },
      error: (error) => {
        alert('Ocorreu um erro inesperado ao salvar o usuário');
      }
    })
  }

}
