import { Location } from '@angular/common';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BaseRouteService } from '../base-route/base-route.service';

@Injectable({
  providedIn: 'root'
})
export class ChooseUserPermissionService implements BaseRouteService {

  constructor(
    private router: Router,
    public location: Location
  ) { }

  goToChooseUserPermission(): void {
    this.router.navigate(['/chooseUserPermission']);
  }

  returnPreviousPage(): void {
    this.location.back();
  }

}
