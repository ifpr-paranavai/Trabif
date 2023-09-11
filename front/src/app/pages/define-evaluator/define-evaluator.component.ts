import { ToastService } from './../../services/pages-services/toast/toast.service';
import { TrabalhoAvaliadorService } from './../../services/api-services/trabalho-avaliador/trabalho-avaliador.service';
import { PermissaoUsuario } from './../../models/permissao-usuario';
import { AreaAvaliador } from 'src/app/models/area-avaliador';
import { AreaTrabalhoService } from './../../services/api-services/area-trabalho/area-trabalho.service';
import { AreaTrabalho } from 'src/app/models/area-trabalho';
import { Trabalho } from 'src/app/models/trabalho';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs';
import { MainService } from 'src/app/services/pages-services/main/main.service';
import { AreaAvaliadorService } from 'src/app/services/api-services/area-avaliador/area-avaliador.service';
import { Usuario } from 'src/app/models/usuario';
import { PermissaoUsuarioService } from 'src/app/services/api-services/permissao-usuario/permissao-usuario.service';

@Component({
  selector: 'app-define-evaluator',
  templateUrl: './define-evaluator.component.html',
  styleUrls: ['./define-evaluator.component.scss'],
})
export class DefineEvaluatorComponent implements OnInit {
  constructor(
    public activatedRoute: ActivatedRoute,
    public mainService: MainService,
    private areaTrabalhoService: AreaTrabalhoService,
    private areaAvaliadorService: AreaAvaliadorService,
    private permissaoUsuarioService: PermissaoUsuarioService,
    private trabalhoAvaliadorService: TrabalhoAvaliadorService,
    private toastService: ToastService
  ) {}

  trabalho: Trabalho = new Trabalho();
  areaTrabalhos: AreaTrabalho[] = [];
  areaAvaliadors: AreaAvaliador[] = [];
  avaliadores: PermissaoUsuario[] = [];
  chooseAvaliadores: Usuario[] = [];
  loading: boolean = false;

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(map(() => window.history.state))
      .subscribe((res) => {
        console.log(res);
        if (!res.id) {
          this.mainService.goToMain();
        } else {
          this.trabalho = res;
        }
      });
    this.areaTrabalhoService
      .getByIdTrabalho(this.trabalho.id!)
      .subscribe((result) => {
        if (result.content) {
          this.areaTrabalhos = result.content;
        }
      });
    this.areaAvaliadorService.getAll().subscribe((result) => {
      if (result.content) {
        this.areaAvaliadors = result.content;
      }
    });
    this.getPermissaoUsuario();
  }

  getPermissaoUsuario(): void {
    this.permissaoUsuarioService
      .getAvaliadoresByIdEvento(this.mainService.getEvent?.id!)
      .subscribe((result) => {
        if (result.content) {
          this.avaliadores = result.content;
        }
      });
  }

  addAvaliador(avaliador: Usuario): void {
    if (!this.chooseAvaliadores.includes(avaliador)) {
      this.chooseAvaliadores.push(avaliador);
    }
  }

  saveTrabalhoAvaliador() {
    this.chooseAvaliadores.forEach((avaliador) => {
      let trabalhoAvaliador: any = new Object();
      trabalhoAvaliador.trabalho = this.trabalho;
      trabalhoAvaliador.trabalho.categoria = this.trabalho.categoriaDTO;
      trabalhoAvaliador.trabalho.evento = this.mainService.getEvent;
      trabalhoAvaliador.usuario = avaliador;
      this.trabalhoAvaliadorService
        .post(trabalhoAvaliador)
        .subscribe((result) => {
          if (result) {
            this.mainService.goToMain();
            this.toastService.showSuccess(
              'Avaliador(es) definidos com sucesso!'
            );
          }
        });
    });
  }
}
