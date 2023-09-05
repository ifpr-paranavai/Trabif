import { Trabalho } from 'src/app/models/trabalho';
import { TrabalhoService } from './../../api-services/trabalho/trabalho.service';
import { Injectable } from '@angular/core';
import { BaseResults } from '../../api-services/base/base.service';

@Injectable({
  providedIn: 'root'
})
export class WorksService {

  constructor(private trabalhoService: TrabalhoService) { }
  trabalhos: Trabalho[] = [];
  donwloadAllEventPDFs(eventoId: number) {

    this.trabalhoService.getAllByEventId(eventoId).subscribe((result) => {
      if (result.content) {
        this.trabalhos = result.content;
      }
    });
    let listPdfs = [];
    listPdfs.push(
      this.trabalhos.forEach((trabalho) => {
        return trabalho.pdf;
      })
    );

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
