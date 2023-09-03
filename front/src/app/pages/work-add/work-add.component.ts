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
import { AutorTrabalhoService } from 'src/app/services/api-services/autor-trabalho/autor-trabalho.service';
import { PermissaoUsuario } from 'src/app/models/permissao-usuario';
import { PermissaoUsuarioService } from 'src/app/services/api-services/permissao-usuario/permissao-usuario.service';
import { Usuario } from 'src/app/models/usuario';
import { Evento } from 'src/app/models/evento';


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
  permissaoUsuario: any = new PermissaoUsuario();
  emailAutores: string[] = [];
  autores: Usuario[] = [];

  constructor(
    private autorTrabalhoService: AutorTrabalhoService,
    private categoriaService: CategoriaService,
    private permissaoUsuarioApiService: PermissaoUsuarioService,
    private trabalhoService: TrabalhoService,
    public mainService: MainService,
    private loginService: LoginService,
    private toastService: ToastService,
    private formBuilde: FormBuilder
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilde.group({
      categoria: [null],
      titulo: [null],
      emailAutor: [null]
    });
    this.getCategorias();
    this.autores.push(this.loginService.getLoggedUser!);
  }

  validateDataToAddWork(): void {
    if (!this.uploadedFile.name || this.uploadedFile.size == 0) {
      this.toastService.showWarn('É necessário adicionar um arquivo e fazer seu upload!');
      return;
    }
    if (!this.form.value.titulo) {
      this.toastService.showWarn('É necessário informar o título do arquivo!');
      return;
    }
    if (!this.form.value.categoria) {
      this.toastService.showWarn('É necessário selecionar uma categoria!');
      return;
    }
    this.loading = true;
    this.sendObj.trabalho = {}
    this.sendObj.trabalho.categoria = this.form.value.categoria;
    this.sendObj.trabalho.titulo = this.form.value.titulo;
    this.sendObj.trabalho.evento = this.mainService.getEvent;

    this.trabalhoService.postWithFile(this.sendObj.trabalho, this.uploadedFile).subscribe({
      next: (result: any) => {
      if (result) {
        this.sendObj.trabalho.id = result.id;
        if (this.autores) {
          this.autores.forEach((autor) => {
            this.addWork(autor);
          });
        }
      }
      this.loading = false;
    },
      error: (error: any) => {
        this.toastService.showError('Ocorreu um erro inesperado ao salvar o trabalho');
        this.loading = false;
      }

    });


  }

  addWork(usuario: Usuario): void {
    this.sendObj.usuario = usuario;
    this.autorTrabalhoService.postWithFile(this.sendObj, this.uploadedFile).subscribe({
      next: (result: any) => {
      if (result) {
        this.toastService.showSuccess(`Trabalho do(a) autor(a) ${usuario.nome} adicionado com sucesso!`);
        this.mainService.goToMain();
      }
      this.loading = false;
    },
      error: (error: any) => {
        this.toastService.showError('Ocorreu um erro inesperado ao finalizar o cadastro');
        this.loading = false;
      }

    });
  }

  addEmailAuthor(): void {
    let email = this.form.value.emailAutor;
    this.emailAutores.push(email);
    this.addAuthors();
    this.emailAutores = [];
    (document.getElementById('emailAutor') as HTMLInputElement)!.value = '';
    this.form.value.emailAutor = [null];
  }

  addAuthors(): void {
    if (this.emailAutores) {
      this.loading = true;
      this.permissaoUsuario.evento = new Evento();
      this.permissaoUsuario.evento = this.mainService.getEvent;
      this.permissaoUsuario.usuario = new Usuario();
      this.emailAutores.forEach(email => {
        this.permissaoUsuario.usuario.email = email;
        this.addAuthor();
      })
    }
  }

  addAuthor(): void {
    this.permissaoUsuarioApiService.postAuthor(this.permissaoUsuario).subscribe((result) => {
      if (result.usuarioDTO) {
        this.autores.push(result.usuarioDTO);
        this.toastService.showSuccess("Autor adicionado com sucesso!");
      }
      this.loading = false;
    });
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
      this.toastService.showSuccess('Upload realizado com sucesso!');
    }

  }

  createStringListOfAuthors() {
    return this.autores.map(item => item.nome ?? item.email).join(', ');
  }

}
