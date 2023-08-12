import { Injectable } from '@angular/core';
import { BaseRouteService } from '../base-route/base-route.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class MyEventService implements BaseRouteService {

  constructor(
    private router: Router,
    public location: Location
  ) { }

  goToMyEvent(): void {
    this.router.navigate(['/myEvent']);
  }

  returnPreviousPage(): void {
    this.location.back();
  }

}
