import { Component } from '@angular/core';
import { Evento } from 'src/app/models/evento';
import { PermissaoUsuario } from 'src/app/models/permissao-usuario';
import { Usuario } from 'src/app/models/usuario';
import { PermissaoUsuarioService } from 'src/app/services/api-services/permissao-usuario/permissao-usuario.service';
import { MainService } from 'src/app/services/pages-services/main/main.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';

@Component({
  selector: 'app-organizer-add',
  templateUrl: './organizer-add.component.html',
  styleUrls: ['./organizer-add.component.scss']
})
export class OrganizerAddComponent {
  permissaoUsuario: any = new PermissaoUsuario();
  sendObj: any = new Object();
  loading = false;

  constructor(
    private permissaoUsuarioApiService: PermissaoUsuarioService,
    public mainService: MainService,
    private toastService: ToastService
  ) {}

  addOrganizer(): void {
    if (this.sendObj.email) {
      this.loading = true;
      this.permissaoUsuario.usuario = new Usuario();
      this.permissaoUsuario.usuario.email = this.sendObj.email;
      this.permissaoUsuario.evento = new Evento();
      this.permissaoUsuario.evento = this.mainService.getEvent;
      this.permissaoUsuarioApiService.postOrganizer(this.permissaoUsuario).subscribe((result) => {
        if (result) {
          this.mainService.goToMain();
          this.toastService.showSuccess("Organizador adicionado com sucesso!");
        }
        this.loading = false;
      });
    }
  }
}
