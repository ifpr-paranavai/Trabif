import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BaseRouteService } from '../base-route/base-route.service';
import { Location } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class RecoverPasswordService implements BaseRouteService {

  constructor(
    private router: Router,
    public location: Location
  ) { }

  goToRecover(): void {
    this.router.navigate(['/recoverPassword']);
  }

  returnPreviousPage(): void {
    this.location.back();
  }
}
