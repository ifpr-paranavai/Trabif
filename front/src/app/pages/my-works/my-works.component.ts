import { AutorTabalho } from 'src/app/models/autor-tabalho';
import { AutorTrabalhoService } from './../../services/api-services/autor-trabalho/autor-trabalho.service';
import { Component, OnInit } from '@angular/core';
import { Trabalho } from 'src/app/models/trabalho';
import { TrabalhoService } from 'src/app/services/api-services/trabalho/trabalho.service';
import { LoginService } from 'src/app/services/pages-services/login/login.service';
import { MainService } from 'src/app/services/pages-services/main/main.service';

@Component({
  selector: 'app-my-works',
  templateUrl: './my-works.component.html',
  styleUrls: ['./my-works.component.scss']
})
export class MyWorksComponent implements OnInit {

  trabalhos: Trabalho[] = [];

  constructor(
    private loginService: LoginService,
    private autorTrabalhoService: AutorTrabalhoService,
    private mainService: MainService
  ) {}

  ngOnInit(): void {
    this.autorTrabalhoService.getAutorTrabalhoByIdUsuarioAndIdEvento(this.loginService.getLoggedUserId!, this.mainService.getEvent?.id!).subscribe((result) => {
      if (result.content) {
        result.content.forEach((element: { trabalhoDTO: Trabalho; }) => {
          this.trabalhos.push(element.trabalhoDTO)
        });
      }
    });
  }
}
