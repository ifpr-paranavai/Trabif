import { CategoriaService } from './../../services/api-services/categoria/categoria.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Categoria } from 'src/app/models/categoria';
import { Trabalho } from 'src/app/models/trabalho';
import { TrabalhoService } from 'src/app/services/api-services/trabalho/trabalho.service';
import { LoginService } from 'src/app/services/pages-services/login/login.service';
import { MainService } from 'src/app/services/pages-services/main/main.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';
import { FileUploadHandlerEvent } from 'primeng/fileupload';


@Component({
  selector: 'app-work-add',
  templateUrl: './work-add.component.html',
  styleUrls: ['./work-add.component.scss']
})
export class WorkAddComponent implements OnInit {
  trabalho: any = new Trabalho();
  sendObj: any = new Object();
  categorias: Categoria[] = [];
  form: FormGroup = new FormGroup('');
  uploadedFile: File = new File([], '');
  loading = false;

  constructor(
    private trabalhoService: TrabalhoService,
    private categoriaService: CategoriaService,
    public mainService: MainService,
    private loginService: LoginService,
    private toastService: ToastService,
    private formBuilde: FormBuilder
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilde.group({
      categoria: [null],
      titulo: [null],
    });
    this.getCategorias();
  }

  addWork(): void {
    if (this.sendObj.email) {
      this.loading = true;
      this.sendObj.categoria = this.form.value.categoria;
      this.sendObj.titulo = this.form.value.titulo;
      this.sendObj.pdf = this.uploadedFile;
      this.sendObj.evento = this.mainService.getEvent;

      this.trabalhoService.post(this.sendObj).subscribe({
        next: (result: any) => {
        if (result) {
          this.mainService.goToMain();
          this.toastService.showSuccess("Trabalho adicionado com sucesso!");
        }
        this.loading = false;
      },
        error: (error: any) => {
          this.toastService.showError('Ocorreu um erro inesperado ao finalizar o cadastro');
          this.loading = false;
        }

    });
    }
  }

  getCategorias(): void {
    this.categoriaService.getAll().subscribe((result) => {
      if (result.content) {
        this.categorias = result.content;
      }
    });
  }

  onUpload(event: FileUploadHandlerEvent) {
    if (event) {
      this.uploadedFile = event.files[0];
    }

  }

}
