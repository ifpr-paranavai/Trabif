import { EventService } from 'src/app/services/pages-services/event/event.service';
import { UsuarioService } from './../../services/api-services/usuario/usuario.service';
import { LoginService } from './../../services/pages-services/login/login.service';
import { Component, Input } from '@angular/core';
import { HomeService } from 'src/app/services/pages-services/home/home.service';
import { Usuario } from 'src/app/models/usuario';

export enum BotaoHeader {
  LOGAR = "Logar",
  DESCONECTAR = "Desconectar",
  NENHUM = "Nenhum",
}

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  @Input() login: boolean = false;

  constructor(
    public homeService: HomeService,
    public loginService: LoginService,
    public eventService: EventService,
    public usuarioService: UsuarioService,
  ) {}

  verifyUserAlreadyLogged(): void {
    if (this.loginService.logged) {
      this.eventService.goToEvent();
    } else {
      this.loginService.goToLogin();
    }
  }

  logout(): void {
    this.loginService.logout();
  }

}
