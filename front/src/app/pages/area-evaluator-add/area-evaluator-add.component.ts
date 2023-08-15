import { AreaService } from 'src/app/services/api-services/area/area.service';
import { Component, OnInit } from '@angular/core';
import { AreaAvaliadorService } from 'src/app/services/api-services/area-avaliador/area-avaliador.service';
import { MainService } from 'src/app/services/pages-services/main/main.service';
import { LoginService } from 'src/app/services/pages-services/login/login.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';
import { AreaAvaliador } from 'src/app/models/area-avaliador';
import { Area } from 'src/app/models/area';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-area-evaluator-add',
  templateUrl: './area-evaluator-add.component.html',
  styleUrls: ['./area-evaluator-add.component.scss']
})
export class AreaEvaluatorAddComponent implements OnInit {
  sendObj: AreaAvaliador = new AreaAvaliador();
  loading = false;
  areas: Area[] = [];
  form: FormGroup = new FormGroup('');

  constructor(
    private areaAvaliadorService: AreaAvaliadorService,
    private areaService: AreaService,
    public mainService: MainService,
    private loginService: LoginService,
    private toastService: ToastService,
    private formBuilde: FormBuilder

  ) {}

  ngOnInit(): void {
    this.form = this.formBuilde.group({
      area: [null]
    });
    this.getArea();
  }

  addArea(): void {
    this.sendObj.usuarioDTO = this.loginService.getLoggedUser!;
    this.sendObj.areaDTO = this.form.value;
    if (this.sendObj.areaDTO?.descricao) {
      this.loading = true;
      this.areaService.post(this.sendObj).subscribe((result) => {
        if (result) {
          this.mainService.goToMain();
          this.toastService.showSuccess("Avaliador tem uma nova área do conhecimento!");
        }
        this.loading = false;
      });
    }
  }

  getArea(): void {
    this.areaService.getAll().subscribe((result) => {
      if (result.content) {
        this.areas = result.content;
      }
    });
  }

}
