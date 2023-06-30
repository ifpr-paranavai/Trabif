import { Trabalho } from 'src/app/models/trabalho';
import { TrabalhoService } from './../../api-services/trabalho/trabalho.service';
import { Injectable } from '@angular/core';
import { BaseResults } from '../../api-services/base/base.service';

@Injectable({
  providedIn: 'root'
})
export class WorksService {

  constructor(private trabalhoService: TrabalhoService) { }
  trabalhos: Trabalho[] = [];
  donwloadAllEventPDFs(eventoId: number) {

    this.trabalhoService.getAllByEventId(eventoId).subscribe((result) => {
      if (result.content) {
        this.trabalhos = result.content;
      }
    });
    let listPdfs = [];
    listPdfs.push(
      this.trabalhos.forEach((trabalho) => {
        return trabalho.pdf;
      })
    );

  }
}
