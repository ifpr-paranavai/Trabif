import { EventoEmailTemplateService } from './../../services/api-services/evento-email-template/evento-email-template.service';
import { Component } from '@angular/core';
import { EventoEmailTemplate } from 'src/app/models/evento-email-template';
import { PermissaoUsuarioService } from 'src/app/services/api-services/permissao-usuario/permissao-usuario.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';

@Component({
  selector: 'app-email-template',
  templateUrl: './email-template.component.html',
  styleUrls: ['./email-template.component.scss']
})
export class EmailTemplateComponent {
  eventEmailTemplate: EventoEmailTemplate[] = [];

  constructor(
    private eventoEmailTemplateService: EventoEmailTemplateService,
    private permissaoUsuarioApiService: PermissaoUsuarioService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.getEmailTemplates();
  }

  deleteEmailTemplate(id: number): void {
    this.eventoEmailTemplateService.delete(id).subscribe((result) => {
      if (!result) {
        this.toastService.showSuccess("Template de email removido com sucesso!");
        this.getEmailTemplates();
      }
    });
  }

  getEmailTemplates(): void {
    this.eventoEmailTemplateService.getByIdEvento(this.permissaoUsuarioApiService.userPermissionEvent[0].eventoDTO?.id!).subscribe((result) => {
      if (result.content) {
        this.eventEmailTemplate = result.content;
      }
    });
  }
}
