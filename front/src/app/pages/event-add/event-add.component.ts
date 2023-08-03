import { LoginService } from 'src/app/services/pages-services/login/login.service';
import { UsuarioService } from './../../services/api-services/usuario/usuario.service';
import { Component } from '@angular/core';
import { Evento } from 'src/app/models/evento';
import { EventoService } from 'src/app/services/api-services/evento/evento.service';
import { EventService } from 'src/app/services/pages-services/event/event.service';

@Component({
  selector: 'app-event-add',
  templateUrl: './event-add.component.html',
  styleUrls: ['./event-add.component.scss']
})
export class EventAddComponent {
  evento: Evento = new Evento;

  constructor(
    private eventoApiService: EventoService,
    public eventService: EventService,
    private loginService: LoginService
  ) {}

  loading = false;

  addEvent(): void {
    if (this.evento.nome) {
      this.loading = true;
      this.eventoApiService.postEvent(this.loginService.getLoggedUserId!, this.evento).subscribe(() => {
        alert("Evento criado com sucesso!");
        this.loading = false;
        this.eventService.goToEvent();
      });
    }
  }

}
