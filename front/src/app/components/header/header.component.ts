
import { EventService } from 'src/app/services/pages-services/event/event.service';
import { UsuarioService } from './../../services/api-services/usuario/usuario.service';
import { LoginService } from './../../services/pages-services/login/login.service';
import { Component, Input, OnInit } from '@angular/core';
import { HomeService } from 'src/app/services/pages-services/home/home.service';
import { Usuario } from 'src/app/models/usuario';
import { MenuItem } from 'primeng/api';
import { MyEventService } from 'src/app/services/pages-services/my-event/my-event.service';

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
export class HeaderComponent implements OnInit {
  @Input() login: boolean = false;

  constructor(
    public homeService: HomeService,
    public loginService: LoginService,
    public eventService: EventService,
    public usuarioService: UsuarioService,
    public myEventService: MyEventService
  ) {}

  items: MenuItem[] | undefined;

  activeItem: MenuItem | undefined;

  ngOnInit() {
      this.items = [
        { label: 'Eventos', icon: 'fa-solid fa-house', command: () => this.eventService.goToEvent() },
        { label: 'Meus Eventos', icon: 'fa-solid fa-person', command: () => this.myEventService.goToMyEvent() },
      ];

      this.activeItem = this.items[0];
  }

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
