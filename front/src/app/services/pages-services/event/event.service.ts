import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BaseRouteService } from '../base-route/base-route.service';
import { Location } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class EventService implements BaseRouteService {

  constructor(
    private router: Router,
    public location: Location
  ) { }
  returnPreviousPage(): void {
    this.location.back();
  }

  goToEvent(): void {
    this.router.navigate(['/event']);
  }
}
