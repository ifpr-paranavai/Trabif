import { Component, OnInit } from '@angular/core';
import { MainService } from './../../services/pages-services/main/main.service';
import { PermissaoUsuarioService } from 'src/app/services/api-services/permissao-usuario/permissao-usuario.service';
import { LoginService } from 'src/app/services/pages-services/login/login.service';
import { Evento } from 'src/app/models/evento';
import { EventoService } from 'src/app/services/api-services/evento/evento.service';
import { PermissaoUsuario } from 'src/app/models/permissao-usuario';


@Component({
  selector: 'app-my-event',
  templateUrl: './my-event.component.html',
  styleUrls: ['./my-event.component.scss']
})
export class MyEventComponent implements OnInit {

  eventos: Evento[] = [];

  constructor(
    private eventoApiService: EventoService,
    private loginService: LoginService,
    private permissaoUsuarioService: PermissaoUsuarioService,
    private mainService: MainService
  ) {}

  ngOnInit(): void {
    this.permissaoUsuarioService.getPermissaoUsuarioByIdUsuario(this.loginService.getLoggedUserId!).subscribe((result) => {
      if (result.content) {
        result.content.forEach((permissaoUsuario: { eventoDTO: Evento; }) => {
          this.eventos.push(permissaoUsuario.eventoDTO);
        });
      }
    });
  }

  acessEvent(evento: Evento): void {
    this.permissaoUsuarioService.getPermissaoUsuarioByIdUsuarioAndIdEvento(this.loginService.getLoggedUserId!, evento.id!).subscribe((result) => {
      if (result.content) {
        this.permissaoUsuarioService.userPermissionEvent = result.content;
        localStorage.setItem('permissaoUsuario', JSON.stringify(result.content));
        localStorage.setItem('evento', JSON.stringify(this.permissaoUsuarioService.userPermissionEvent[0].eventoDTO));
        this.mainService.goToMain();
      }
    });
  }
}
