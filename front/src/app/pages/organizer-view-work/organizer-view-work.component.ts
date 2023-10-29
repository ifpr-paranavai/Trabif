import { Component, OnInit } from '@angular/core';
import { TrabalhoAvaliadorService } from './../../services/api-services/trabalho-avaliador/trabalho-avaliador.service';
import { TrabalhoAvaliador } from './../../models/trabalho-avaliador';
import { ToastService } from '../../services/pages-services/toast/toast.service';
import { MainService } from '../../services/pages-services/main/main.service';
import { TrabalhoService } from '../../services/api-services/trabalho/trabalho.service';
import { Trabalho } from 'src/app/models/trabalho';
import { WorksService } from 'src/app/services/pages-services/works/works.service';
import { async } from 'rxjs';

interface ItemToShow {
  trabalho: Trabalho;
  expanded: boolean;
  trabalhoAvaliador: TrabalhoAvaliador[];
  loading: boolean;
}


@Component({
  selector: 'app-organizer-view-work',
  templateUrl: './organizer-view-work.component.html',
  styleUrls: ['./organizer-view-work.component.scss']
})
export class OrganizerViewWorkComponent  implements OnInit {
  constructor(
    private mainService: MainService,
    private trabalhoService: TrabalhoService,
    private trabalhoAvaliadorService: TrabalhoAvaliadorService,
    private worksService: WorksService,
    private toastService: ToastService
  ) { }

  trabalhos: Trabalho[] = [];
  items: ItemToShow[] = [];
  loading = false;

  ngOnInit(): void {
    this.getTrabalhos();
  }
  getTrabalhos() {

    this.trabalhoService.getAllByEventId(this.mainService.getEvent?.id!).subscribe((result) => {
      if (result.content) {
        result.content.forEach((trabalho) => {
          let item: ItemToShow = {
            trabalho: trabalho,
            expanded: false,
            trabalhoAvaliador: [],
            loading: false
          }
          this.items.push(item);
          this.getTrabalhosAvaliador(item);
        });
        this.trabalhos = result.content;
      }
    })
  }

  aproveWork(item: Trabalho) {
    let sendObj: any = new Object();
    sendObj.id = item.id;
    sendObj.titulo = item.titulo;
    sendObj.resultado = "Aprovado";
    sendObj.evento = item.eventoDTO;
    sendObj.categoria = item.categoriaDTO;

    this.trabalhoService.put(item.id!, sendObj).subscribe((result) => {
      this.toastService.showSuccess('Trabalho aprovado com sucesso!');
    })
  }

  reproveWork(item: Trabalho) {
    let sendObj: any = new Object();
    sendObj.id = item.id;
    sendObj.titulo = item.titulo;
    sendObj.resultado = "Reprovado";
    sendObj.evento = item.eventoDTO;
    sendObj.categoria = item.categoriaDTO;

    this.trabalhoService.put(item.id!, sendObj).subscribe((result) => {
      this.toastService.showSuccess('Trabalho reprovado com sucesso!');
    })
  }

  expandItem(item: ItemToShow) {
    item.expanded = !item.expanded;
  }

  getTrabalhosAvaliador(item: ItemToShow) {

    this.trabalhoAvaliadorService.getAllByIdTrabalho(item.trabalho.id!).subscribe((result) => {
      if (result.content) {
        item.trabalhoAvaliador = result.content;
      }
      item.loading = false;
    });
  }

  downloadAllWorks() {
    this.trabalhos.forEach(async (trabalho) => {
      await this.downloadWork(trabalho);
    })
  }

  downloadWork(trabalho: Trabalho): void {
    let blob = this.worksService.b64toBlob(trabalho.pdf!, 'application/pdf');
    let url = window.URL.createObjectURL(blob);
    let link = document.createElement('a');
    link.href = url;
    link.download = trabalho.titulo!;
    link.click();
    window.URL.revokeObjectURL(url);
    link.remove();
  }

}
