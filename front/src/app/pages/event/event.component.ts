import { ChooseUserPermissionService } from './../../services/pages-services/choose-user-permission/choose-user-permission.service';
import { MainService } from './../../services/pages-services/main/main.service';
import { UsuarioService } from './../../services/api-services/usuario/usuario.service';
import { Usuario } from './../../models/usuario';
import { Component, OnInit } from '@angular/core';
import { Evento } from 'src/app/models/evento';
import { EventoService } from 'src/app/services/api-services/evento/evento.service';
import { PermissaoUsuarioService } from 'src/app/services/api-services/permissao-usuario/permissao-usuario.service';
import { LoginService } from 'src/app/services/pages-services/login/login.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.scss'],
})
export class EventComponent implements OnInit {

  eventos: Evento[] = [];

  constructor(
    private eventoApiService: EventoService,
    private loginService: LoginService,
    private permissaoUsuarioService: PermissaoUsuarioService,
    private chooseUserPermissionService: ChooseUserPermissionService,
    private mainService: MainService
  ) {}

  ngOnInit(): void {
    this.eventoApiService.getAll().subscribe((result) => {
      if (result.content) {
        this.eventos = result.content;
      }
    });
  }

  acessEvent(evento: Evento): void {
    this.permissaoUsuarioService.getPermissaoUsuarioByIdUsuarioAndIdEvento(this.loginService.getLoggedUserId!, evento.id!).subscribe((result) => {
      if (result.content) {
        this.permissaoUsuarioService.userPermissionEvent = result.content;
        localStorage.setItem('permissaoUsuario', JSON.stringify(result.content));
        localStorage.setItem('evento', JSON.stringify(this.permissaoUsuarioService.userPermissionEvent[0].eventoDTO));
        if (this.permissaoUsuarioService.userPermissionEvent.length > 1) {
          this.chooseUserPermissionService.goToChooseUserPermission();
        } else {
          localStorage.setItem('permissaoUsuarioEscolhida', JSON.stringify(this.permissaoUsuarioService.userPermissionEvent[0]));
          this.mainService.goToMain();
        }
      }
    });
  }
}
