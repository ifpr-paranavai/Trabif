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

  trabalho: Trabalho = new Trabalho();
  emailTemplates: EmailTemplate[] = [];
  autorTrabalhos: AutorTrabalho[] = [];
  selectedTemplate: EmailTemplate = new EmailTemplate();
  autorTrabalho = new AutorTrabalho();
  loading: boolean = false;
  form: FormGroup = new FormGroup('');

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
    this.emailTemplateService
      .getAll()
      .subscribe((result) => {
        if (result.content) {
          this.emailTemplates = result.content;
        }
      });
    this.autorTrabalhoService.getAutorTrabalhoByIdTrabalho(this.trabalho.id!).subscribe((result) => {
      if (result.content) {
        this.autorTrabalhos = result.content;
      }
    });
    this.form = this.formBuilde.group({
      template: [null]
    });
  }

  sendEmail() {
    this.selectedTemplate = this.form.value.template
    let sendObj = {
      mensagem: this.selectedTemplate.mensagem,
      nomeEvento: this.mainService.getEvent?.nome,
      nomeUsuario: this.autorTrabalho.usuarioDTO?.nome,
      tituloTrabalho: this.trabalho.titulo,
      resultadoFinalTrabalho: this.trabalho.resultado
    }
    this.autorTrabalhos.forEach(async (autorTrabalho) => {
      sendObj.nomeUsuario = autorTrabalho.usuarioDTO?.nome
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
  }
}

