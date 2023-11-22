import { AutorTrabalhoService } from 'src/app/services/api-services/autor-trabalho/autor-trabalho.service';
import { EmailService } from './../../services/api-services/email/email.service';
import { EventoEmailTemplateService } from './../../services/api-services/evento-email-template/evento-email-template.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { map, async } from 'rxjs';
import { EventoEmailTemplate } from 'src/app/models/evento-email-template';
import { Trabalho } from 'src/app/models/trabalho';
import { MainService } from 'src/app/services/pages-services/main/main.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';
import { AutorTrabalho } from 'src/app/models/autor-trabalho';
import { FormBuilder, FormGroup } from '@angular/forms';
import { EmailTemplate } from 'src/app/models/email-template';
import { EmailTemplateService } from 'src/app/services/api-services/email-template/email-template.service';

interface ItemTrabalho {
  trabalho: Trabalho;
  autorTrabalhos: AutorTrabalho[];
}

@Component({
  selector: 'app-send-email',
  templateUrl: './send-email.component.html',
  styleUrls: ['./send-email.component.scss']
})
export class SendEmailComponent implements OnInit {
  constructor(
    public activatedRoute: ActivatedRoute,
    public mainService: MainService,
    private emailTemplateService: EmailTemplateService,
    private emailService: EmailService,
    private autorTrabalhoService: AutorTrabalhoService,
    private toastService: ToastService,
    private formBuilde: FormBuilder
  ) {}

  trabalhos: Trabalho[] = [];
  items: ItemTrabalho[] = [];
  emailTemplates: EmailTemplate[] = [];
  selectedTemplate: EmailTemplate = new EmailTemplate();
  autorTrabalho = new AutorTrabalho();
  loading: boolean = false;
  form: FormGroup = new FormGroup('');
  templateMessage: any = new EmailTemplate();

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(map(() => window.history.state))
      .subscribe((res) => {
        console.log(res);
        if (!res[0][0].id) {
          this.mainService.goToMain();
        } else {
          this.trabalhos = res[0];
        }
      });
    this.emailTemplateService
      .getAll()
      .subscribe((result) => {
        if (result.content) {
          this.emailTemplates = result.content;
        }
      });

    for (let i = 0; i < this.trabalhos.length; i++) {
      this.autorTrabalhoService.getAutorTrabalhoByIdTrabalho(this.trabalhos[i].id!).subscribe((result) => {
        if (result.content) {
          this.items.push({
            trabalho: this.trabalhos[i],
            autorTrabalhos: result.content
          });
        }
      });
    }
    this.form = this.formBuilde.group({
      template: [null],
      templateMessage: [null],
    });
  }

  sendEmail() {
    this.items.forEach(item => {
      if (this.form.value.template) {
        this.selectedTemplate = this.form.value.template;
      }
      else {
        this.templateMessage.mensagem = this.form.value.templateMessage;
        this.selectedTemplate = this.templateMessage;
      }

      item.autorTrabalhos.forEach(async (autorTrabalho) => {
        let sendObj = {
          mensagem: this.selectedTemplate.mensagem,
          nomeEvento: this.mainService.getEvent?.nome,
          nomeUsuario: autorTrabalho.usuarioDTO?.nome,
          tituloTrabalho: item.trabalho.titulo,
          resultadoFinalTrabalho: item.trabalho.resultado
        }
        await this.emailService.sendEmail(autorTrabalho.usuarioDTO?.email!, sendObj).subscribe({
          next: (res) => {
            this.toastService.showSuccess("E-mail enviado com sucesso!");
            this.mainService.goToMain();
          },
          error: (err) => {
            this.toastService.showError("Ocorreu um erro ao enviar o e-mail!");
          }
        });
      });
    })

  }
}

