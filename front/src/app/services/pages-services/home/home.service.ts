import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BaseRouteService } from '../base-route/base-route.service';
import { Location } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class HomeService implements BaseRouteService {

  constructor(
    private router: Router,
    public location: Location
  ) { }

  goToHome(): void {
    this.router.navigate(['/home']);
  }

  returnPreviousPage(): void {
    this.location.back();
  }
}
