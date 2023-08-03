import { Location } from '@angular/common';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class BaseRouteService {

  constructor(
    public location: Location
  ) { }

  returnPreviousPage(): void {
    this.location.back();
  }
}
