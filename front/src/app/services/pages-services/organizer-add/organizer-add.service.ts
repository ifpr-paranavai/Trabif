import { Injectable } from '@angular/core';
import { BaseRouteService } from '../base-route/base-route.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class OrganizerAddService implements BaseRouteService {

  constructor(
    private router: Router,
    public location: Location
  ) { }

  goToOrganizerAdd(): void {
    this.router.navigate(['/organizerAdd']);
  }

  returnPreviousPage(): void {
    this.location.back();
  }
}
