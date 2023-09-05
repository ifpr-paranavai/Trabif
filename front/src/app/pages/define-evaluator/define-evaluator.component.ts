import { AreaAvaliador } from 'src/app/models/area-avaliador';
import { AreaTrabalhoService } from './../../services/api-services/area-trabalho/area-trabalho.service';
import { AreaTrabalho } from 'src/app/models/area-trabalho';
import { Trabalho } from 'src/app/models/trabalho';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs';
import { MainService } from 'src/app/services/pages-services/main/main.service';
import { AreaAvaliadorService } from 'src/app/services/api-services/area-avaliador/area-avaliador.service';

@Component({
  selector: 'app-define-evaluator',
  templateUrl: './define-evaluator.component.html',
  styleUrls: ['./define-evaluator.component.scss']
})
export class DefineEvaluatorComponent implements OnInit {
 constructor(
  public activatedRoute: ActivatedRoute,
  public mainService: MainService,
  private areaTrabalhoService: AreaTrabalhoService,
  private areaAvaliadorService: AreaAvaliadorService
 ) { }

 trabalho: Trabalho = new Trabalho();
 areaTrabalhos: AreaTrabalho[] = [];
 areaAvaliadors: AreaAvaliador[] = [];

 ngOnInit(): void {
  this.activatedRoute.paramMap
    .pipe(map(() => window.history.state)).subscribe(res=>{
          console.log(res)
          if (!res.id) {
            this.mainService.goToMain();
          } else {
            this.trabalho = res;
          }
     });
    this.areaTrabalhoService.getByIdTrabalho(this.trabalho.id!).subscribe((result) => {
      if (result.content) {
        this.areaTrabalhos = result.content;
      }
    })
    this.areaAvaliadorService.getAll().subscribe((result) => {
      if (result.content) {
        this.areaAvaliadors = result.content;
      }
    })
  }
}
