import { Component } from '@angular/core';
import { AreaAvaliador } from 'src/app/models/area-avaliador';
import { AreaAvaliadorService } from 'src/app/services/api-services/area-avaliador/area-avaliador.service';
import { AreaService } from 'src/app/services/api-services/area/area.service';
import { LoginService } from 'src/app/services/pages-services/login/login.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';

@Component({
  selector: 'app-area-evaluator',
  templateUrl: './area-evaluator.component.html',
  styleUrls: ['./area-evaluator.component.scss']
})
export class AreaEvaluatorComponent {

  areaAvaliadores: AreaAvaliador[] = [];

  constructor(
    private areaAvaliadorService: AreaAvaliadorService,
    private loginService: LoginService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.getArea();
  }

  deleteArea(id: number): void {
    this.areaAvaliadorService.delete(id).subscribe((result) => {
      if (!result) {
        this.toastService.showSuccess("Avaliador não tem mais essa área do conhecimento!");
        this.getArea();
      }
    });
  }

  getArea(): void {
    this.areaAvaliadorService.getAreaAvaliadorByIdAvaliador(this.loginService.getLoggedUserId!).subscribe((result) => {
      if (result.content) {
        this.areaAvaliadores = result.content;
      }
    });
  }
}
