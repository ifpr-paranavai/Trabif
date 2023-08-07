import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Usuario } from 'src/app/models/usuario';
import { UsuarioService } from 'src/app/services/api-services/usuario/usuario.service';
import { HomeService } from 'src/app/services/pages-services/home/home.service';
import { LoginService } from 'src/app/services/pages-services/login/login.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';

@Component({
  selector: 'app-finalize-registration',
  templateUrl: './finalize-registration.component.html',
  styleUrls: ['./finalize-registration.component.scss']
})
export class FinalizeRegistrationComponent implements OnInit {
  constructor(
    private usuarioService: UsuarioService,
    private route: ActivatedRoute,
    private toastService: ToastService,
    public homeService: HomeService,
    public loginService: LoginService
  ) { }

  usuario: Usuario = new Usuario();
  loading = false;

  ngOnInit(): void {
    if (this.route.snapshot.paramMap.get('id')) {
      let id: number = parseInt(this.route.snapshot.paramMap.get('id')!);
      this.loading = true;
      this.usuarioService.getById(id).subscribe((result) => {
        this.loading = false;
        if (result.content) {
          this.usuario = result.content;
        }
        console.log(result);
      });
    }


  }

  saveUsuario(): void {
    this.loading = true;
    this.usuarioService.put(this.usuario.id!, this.usuario).subscribe({
      next: (result) => {
        if (result) {
          this.toastService.showSuccess('O cadastro foi fionalizado com sucesso!');
          this.loginService.goToLogin();
        }
        this.loading = false;
      },
      error: (error) => {
        this.toastService.showError('Ocorreu um erro inesperado ao finalizar o cadastro');
        this.loading = false;
      }
    })
  }

}
