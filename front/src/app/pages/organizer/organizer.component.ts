import { Component } from '@angular/core';
import { PermissaoUsuario } from 'src/app/models/permissao-usuario';
import { PermissaoUsuarioService } from 'src/app/services/api-services/permissao-usuario/permissao-usuario.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';

@Component({
  selector: 'app-organizer',
  templateUrl: './organizer.component.html',
  styleUrls: ['./organizer.component.scss']
})
export class OrganizerComponent {
  userPermissions: PermissaoUsuario[] = [];

  constructor(
    private permissaoUsuarioApiService: PermissaoUsuarioService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.getUserPermissions();
  }

  deleteUserPermission(id: number): void {
    this.permissaoUsuarioApiService.delete(id).subscribe({
      next: (result) => {
        if (!result) {
          this.toastService.showSuccess("Organizador removido com sucesso!");
          this.getUserPermissions();
        }
      },
      error: (error) => {
        this.toastService.showError(error.error.message);
      }
    });
  }

  getUserPermissions(): void {
    this.permissaoUsuarioApiService.getOrganizadoresByIdEvento(this.permissaoUsuarioApiService.userPermissionEvent[0].eventoDTO?.id!).subscribe((result) => {
      if (result.content) {
        this.userPermissions = result.content;
      }
    });
  }
}
