import { AutorTrabalho } from 'src/app/models/autor-trabalho';
import { AutorTrabalhoService } from './../../services/api-services/autor-trabalho/autor-trabalho.service';
import { Component, OnInit } from '@angular/core';
import { Trabalho } from 'src/app/models/trabalho';
import { TrabalhoService } from 'src/app/services/api-services/trabalho/trabalho.service';
import { LoginService } from 'src/app/services/pages-services/login/login.service';
import { MainService } from 'src/app/services/pages-services/main/main.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';

@Component({
  selector: 'app-my-works',
  templateUrl: './my-works.component.html',
  styleUrls: ['./my-works.component.scss']
})
export class MyWorksComponent implements OnInit {

  trabalhos: Trabalho[] = [];
  areaTrabalhos: AutorTrabalho[] = [];

  constructor(
    private loginService: LoginService,
    private autorTrabalhoService: AutorTrabalhoService,
    private trabalhoService: TrabalhoService,
    private mainService: MainService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.autorTrabalhoService.getAutorTrabalhoByIdUsuarioAndIdEvento(this.loginService.getLoggedUserId!, this.mainService.getEvent?.id!).subscribe((result) => {
      if (result.content) {
        result.content.forEach((element: { trabalhoDTO: Trabalho; }) => {
          this.trabalhos.push(element.trabalhoDTO);
        });
        this.areaTrabalhos = result.content;
      }
    });
  }

  downloadWork(trabalho: Trabalho): void {
    let blob = this.b64toBlob(trabalho.pdf!, 'application/pdf');
    let url = window.URL.createObjectURL(blob);
    let link = document.createElement('a');
    link.href = url;
    link.download = trabalho.titulo!;
    link.click();
    window.URL.revokeObjectURL(url);
    link.remove();
  }

  deleteAuthorWork(id: number): void {
    let autorTrabalho = new AutorTrabalho();
    this.areaTrabalhos.forEach((element) => {
      if (element.trabalhoDTO?.id == id) {
        autorTrabalho = element;
      }
    })
    if (autorTrabalho.id) {
      this.autorTrabalhoService.delete(autorTrabalho.id).subscribe((result) => {
        if (!result) {
          this.trabalhoService.delete(id).subscribe((result) => {
            if (!result) {
              this.toastService.showSuccess("Trabalho removido com sucesso!");
            } else {
              this.toastService.showError("Erro ao remover trabalho!");
            }
          });
        }
      });
    }
  }

  b64toBlob(b64Data:any, contentType:any) {
    const sliceSize = 512;
    const byteCharacters = atob(b64Data);
    const byteArrays = [];

    for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
      const slice = byteCharacters.slice(offset, offset + sliceSize);

      const byteNumbers = new Array(slice.length);
      for (let i = 0; i < slice.length; i += 1) {
        byteNumbers[i] = slice.charCodeAt(i);
      }

      const byteArray = new Uint8Array(byteNumbers);
      byteArrays.push(byteArray);
    }

    return new Blob(byteArrays, { type: contentType });
  }

}
