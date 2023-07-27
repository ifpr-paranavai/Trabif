import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/models/usuario';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(
    private router: Router,
  ) { }

  goToLogin(): void {
    this.router.navigate(['/login']);
  }

  logout() {
    localStorage.clear();
    this.goToLogin();
  }
  get getLoggedUser(): Usuario | null {
    return localStorage.getItem('usuario') ?
      JSON.parse(localStorage.getItem('usuario')!) as Usuario : null;
  }
  get getLoggedUserId(): number | null | undefined {
    return localStorage.getItem('usuario')
      ? (JSON.parse(localStorage.getItem('usuario')!) as Usuario).id
      : null;
  }
  get getUserToken(): string | null {
    return localStorage.getItem('token')
      ? localStorage.getItem('token')
      : null;
  }
  get logged(): boolean {
    return localStorage.getItem('token') ? true : false;
  }
}
