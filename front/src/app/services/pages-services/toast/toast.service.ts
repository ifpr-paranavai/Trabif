import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  constructor(
    private messageService: MessageService
  ) { }

  showSuccess(message?: string, titulo?: string) {
    this.messageService.add({ severity: 'success', summary: titulo ?? 'Sucesso', detail: message ?? 'Operação realizada com sucesso'});
  }

  showInfo(message: string, titulo?: string) {
    this.messageService.add({ severity: 'info', summary: 'Informação' ?? titulo, detail: message });
  }

  showWarn(message: string, titulo?: string) {
    this.messageService.add({ severity: 'warn', summary: titulo ?? 'Atenção', detail: message });
  }

  showError(message?: string, titulo?: string) {
    this.messageService.add({ severity: 'error', summary: titulo ?? 'Erro', detail: message ?? 'Ocorreu um erro' });
  }
}
