import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BaseRouteService } from '../base-route/base-route.service';
import { Location } from '@angular/common';

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
}
