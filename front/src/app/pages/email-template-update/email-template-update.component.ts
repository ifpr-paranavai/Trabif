import { EmailTemplateService } from './../../services/api-services/email-template/email-template.service';
import { EventoEmailTemplateService } from '../../services/api-services/evento-email-template/evento-email-template.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs';
import { EmailTemplate } from 'src/app/models/email-template';
import { Evento } from 'src/app/models/evento';
import { EventoEmailTemplate } from 'src/app/models/evento-email-template';
import { PermissaoUsuarioService } from 'src/app/services/api-services/permissao-usuario/permissao-usuario.service';
import { MainService } from 'src/app/services/pages-services/main/main.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';

@Component({
  selector: 'app-email-template-update',
  templateUrl: './email-template-update.component.html',
  styleUrls: ['./email-template-update.component.scss']
})
export class EmailTemplateUpdateComponent implements OnInit {
  eventoEmailTemplate: any = new EventoEmailTemplate();
  sendObj: any = new Object();
  loading = false;

  constructor(
    public activatedRoute: ActivatedRoute,
    private permissaoUsuarioApiService: PermissaoUsuarioService,
    private emailTemplateService: EmailTemplateService,
    private eventoEmailTemplateService: EventoEmailTemplateService,
    public mainService: MainService,
    private toastService: ToastService
  ) {}
  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(map(() => window.history.state)).subscribe(res=>{
            console.log(res)
            if (!res.emailTemplateDTO) {
              this.mainService.goToMain();
            } else {
              this.sendObj = res.emailTemplateDTO;
            }

       });
  }

  addEmailTemplate(): void {
    if (this.sendObj.mensagem) {
      this.loading = true;
      this.emailTemplateService.put(this.sendObj.id, this.sendObj).subscribe((result) => {
        if (result) {
          this.mainService.goToMain();
          this.toastService.showSuccess("Template de e-mail atualizado com sucesso!");
        }
        this.loading = false;
      });
    }
  }
}
