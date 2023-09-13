import { WorksService } from './../../services/pages-services/works/works.service';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs';
import { ResultadoSubmissao } from 'src/app/models/resultado-submissao';
import { Trabalho } from 'src/app/models/trabalho';
import { TrabalhoAvaliador } from 'src/app/models/trabalho-avaliador';
import { TrabalhoAvaliadorService } from 'src/app/services/api-services/trabalho-avaliador/trabalho-avaliador.service';
import { MainService } from 'src/app/services/pages-services/main/main.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';

@Component({
  selector: 'app-evaluate-work',
  templateUrl: './evaluate-work.component.html',
  styleUrls: ['./evaluate-work.component.scss']
})
export class EvaluateWorkComponent {
  constructor(
    public activatedRoute: ActivatedRoute,
    public mainService: MainService,
    private trabalhoAvaliadorService: TrabalhoAvaliadorService,
    private worksService: WorksService,
    private toastService: ToastService
  ) {}

  trabalhoAvaliador: TrabalhoAvaliador = new TrabalhoAvaliador();
  loading: boolean = false;

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(map(() => window.history.state))
      .subscribe((res) => {
        console.log(res);
        if (!res.id) {
          this.mainService.goToMain();
        } else {
          this.trabalhoAvaliador = res;
          this.trabalhoAvaliador.resultadoSubmissaoDTO = new ResultadoSubmissao();
        }
      });
  }

  defineGrade(grade: number) {
    if (grade) {
      this.trabalhoAvaliador.resultadoSubmissaoDTO!.resultado = grade;
    }
  }
  defineConfidence(grade: number) {
    if (grade) {
      this.trabalhoAvaliador.resultadoSubmissaoDTO!.confianca = grade;
    }
  }

  saveTrabalhoAvaliador() {
    this.trabalhoAvaliadorService.put(this.trabalhoAvaliador.id!, this.trabalhoAvaliador).subscribe((result) => {
      if (result) {
        this.mainService.goToMain();
        this.toastService.showSuccess('Avaliador(es) definidos com sucesso!');
      }
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
