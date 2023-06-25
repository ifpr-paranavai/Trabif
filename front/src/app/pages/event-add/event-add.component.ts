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
    private eventService: EventService
  ) {}

  addEvent(): void {
    if (this.evento.nome) {
      this.eventoApiService.post(this.evento).subscribe(() => {
        alert("Evento criado com sucesso!");
        this.eventService.irParaEvent();
      });
    }
  }

}
