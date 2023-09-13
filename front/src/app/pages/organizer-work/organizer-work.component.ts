import { TrabalhoAvaliadorService } from './../../services/api-services/trabalho-avaliador/trabalho-avaliador.service';
import { TrabalhoAvaliador } from './../../models/trabalho-avaliador';
import { ToastService } from '../../services/pages-services/toast/toast.service';
import { MainService } from '../../services/pages-services/main/main.service';
import { TrabalhoService } from '../../services/api-services/trabalho/trabalho.service';
import { Component, OnInit } from '@angular/core';
import { Trabalho } from 'src/app/models/trabalho';
import { WorksService } from 'src/app/services/pages-services/works/works.service';

interface ItemToShow {
  trabalho: Trabalho;
  expanded: boolean;
  trabalhoAvaliador: TrabalhoAvaliador[];
  loading: boolean;
}

@Component({
  selector: 'app-organizer-work',
  templateUrl: './organizer-work.component.html',
  styleUrls: ['./organizer-work.component.scss']
})
export class OrganizerWorkComponent implements OnInit {
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

    this.trabalhoService.put(item.id!, sendObj).subscribe((result) => {
      this.toastService.showSuccess('Trabalho aprovado com sucesso!');
    })
  }

  reproveWork(item: Trabalho) {
    let sendObj: any = new Object();
    sendObj.id = item.id;
    sendObj.titulo = item.titulo;
    sendObj.resultado = "Reprovado";

    this.trabalhoService.put(item.id!, sendObj).subscribe((result) => {
      this.toastService.showSuccess('Trabalho reprovado com sucesso!');
    })
  }

  expandItem(item: ItemToShow) {
    item.expanded = !item.expanded;
    item.loading = true;
    if (item.expanded && item.trabalhoAvaliador.length == 0) {
      this.getTrabalhosAvaliador(item);
    } else {
      item.loading = false;
    }
  }

  getTrabalhosAvaliador(item: ItemToShow) {

    this.trabalhoAvaliadorService.getAllByIdTrabalho(item.trabalho.id!).subscribe((result) => {
      if (result.content) {
        item.trabalhoAvaliador = result.content;
      }
      item.loading = false;
    });
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
