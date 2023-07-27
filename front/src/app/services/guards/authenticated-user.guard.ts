import { LoginService } from './../pages-services/login/login.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { EventService } from '../pages-services/event/event.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticatedUserGuard implements CanActivate {
  constructor(
    public loginService: LoginService,
  ) {}
  canActivate() {
    if (this.loginService.logged) {
      return true;
    }
    this.loginService.goToLogin();
    return false;
  }

}
