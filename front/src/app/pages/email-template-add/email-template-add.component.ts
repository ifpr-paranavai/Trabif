import { EventoEmailTemplateService } from './../../services/api-services/evento-email-template/evento-email-template.service';
import { Component } from '@angular/core';
import { EmailTemplate } from 'src/app/models/email-template';
import { Evento } from 'src/app/models/evento';
import { EventoEmailTemplate } from 'src/app/models/evento-email-template';
import { PermissaoUsuarioService } from 'src/app/services/api-services/permissao-usuario/permissao-usuario.service';
import { MainService } from 'src/app/services/pages-services/main/main.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';

@Component({
  selector: 'app-email-template-add',
  templateUrl: './email-template-add.component.html',
  styleUrls: ['./email-template-add.component.scss']
})
export class EmailTemplateAddComponent {
  eventoEmailTemplate: any = new EventoEmailTemplate();
  sendObj: any = new Object();
  loading = false;

  constructor(
    private permissaoUsuarioApiService: PermissaoUsuarioService,
    private eventoEmailTemplateService: EventoEmailTemplateService,
    public mainService: MainService,
    private toastService: ToastService
  ) {}

  addEmailTemplate(): void {
    if (this.sendObj.mensagem) {
      this.loading = true;
      this.sendObj.emailTemplate = new EmailTemplate();
      this.sendObj.evento = new Evento();
      this.sendObj.evento = this.permissaoUsuarioApiService.userPermissionEvent[0].eventoDTO;
      this.eventoEmailTemplateService.postNewTemplate(this.sendObj).subscribe((result) => {
        if (result) {
          this.mainService.goToMain();
          this.toastService.showSuccess("Template de e-mail adicionado com sucesso!");
        }
        this.loading = false;
      });
    }
  }
}
