import { Location } from '@angular/common';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BaseRouteService } from '../base-route/base-route.service';

@Injectable({
  providedIn: 'root'
})
export class RegisterService implements BaseRouteService {

  constructor(
    private router: Router,
    public location: Location
  ) { }

  goToRegister(): void {
    this.router.navigate(['/register']);
  }

  returnPreviousPage(): void {
    this.location.back();
  }
}
