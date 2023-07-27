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
        this.mainService.goToMain();
      }
    });
  }
}
