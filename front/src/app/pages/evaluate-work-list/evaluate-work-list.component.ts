import { Component, OnInit } from '@angular/core';
import { AreaTrabalho } from 'src/app/models/area-trabalho';
import { TrabalhoAvaliador } from 'src/app/models/trabalho-avaliador';
import { TrabalhoAvaliadorService } from 'src/app/services/api-services/trabalho-avaliador/trabalho-avaliador.service';
import { LoginService } from 'src/app/services/pages-services/login/login.service';
import { MainService } from 'src/app/services/pages-services/main/main.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';

@Component({
  selector: 'app-evaluate-work-list',
  templateUrl: './evaluate-work-list.component.html',
  styleUrls: ['./evaluate-work-list.component.scss']
})
export class EvaluateWorkListComponent implements OnInit {
  constructor(
    private mainService: MainService,
    private loginService: LoginService,
    private trabalhoAvaliadorService: TrabalhoAvaliadorService,
    private toastService: ToastService
  ) { }


  trabalhoAvaliadores: TrabalhoAvaliador[] = [];

  ngOnInit(): void {
    this.getTrabalhosAvaliador();
  }

  getTrabalhosAvaliador() {

    this.trabalhoAvaliadorService.getAllByIdAvaliador(this.loginService.getLoggedUserId!).subscribe((result) => {
      if (result.content) {
        this.trabalhoAvaliadores = result.content;
      }
    });
  }
}
