import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BaseRouteService } from '../base-route/base-route.service';
import { Location } from '@angular/common';
import { PermissaoUsuario } from 'src/app/models/permissao-usuario';
import { Evento } from 'src/app/models/evento';

@Injectable({
  providedIn: 'root'
})
export class MainService implements BaseRouteService {

  constructor(
    private router: Router,
    public location: Location
  ) { }

  goToMain(): void {
    this.router.navigate(['/main']);
  }

  returnPreviousPage(): void {
    this.location.back();
  }

  get getUserPermission(): PermissaoUsuario[] | null {
    return localStorage.getItem('permissaoUsuario') ?
      JSON.parse(localStorage.getItem('permissaoUsuario')!) as PermissaoUsuario[] : null;
  }
  get getEvent(): Evento | null {
    return localStorage.getItem('evento') ?
      JSON.parse(localStorage.getItem('evento')!) as Evento : null;
  }
}
