import { Component, OnInit } from '@angular/core';
import { Evento } from 'src/app/models/evento';
import { EventoService } from 'src/app/services/api-services/evento/evento.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.scss'],
})
export class EventComponent implements OnInit {

  eventos: Evento[] = [];

  constructor(private eventoApiService: EventoService) {}

  ngOnInit(): void {
    this.eventoApiService.getAll().subscribe((result) => {
      if (result.results) {
        this.eventos = result.results;
      }
    });
  }
}
