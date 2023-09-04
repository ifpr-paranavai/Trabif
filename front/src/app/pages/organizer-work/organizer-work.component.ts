import { ToastService } from '../../services/pages-services/toast/toast.service';
import { MainService } from '../../services/pages-services/main/main.service';
import { TrabalhoService } from '../../services/api-services/trabalho/trabalho.service';
import { Component, OnInit } from '@angular/core';
import { Trabalho } from 'src/app/models/trabalho';
import { TrabalhoAvaliador } from 'src/app/models/trabalho-avaliador';

interface ItemToShow {
  trabalho: Trabalho;
  expanded: boolean;
  trabalhoAvaliador: TrabalhoAvaliador[];
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
    private toastService: ToastService
  ) { }

  trabalhos: Trabalho[] = [];
  items: ItemToShow[] = [];

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
            trabalhoAvaliador: []
          }
          this.items.push(item);
        });
        this.trabalhos = result.content;
      }
    })
  }

}
