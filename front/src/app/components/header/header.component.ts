import { Component, Input } from '@angular/core';
import { HomeService } from 'src/app/services/home/home.service';

export enum BotaoHeader {
  LOGAR = "Logar",
  DESCONECTAR = "Desconectar",
  NENHUM = "Nenhum",
}

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  @Input() login: boolean = false;

  constructor(
    public homeService: HomeService,
  ) {}

}
