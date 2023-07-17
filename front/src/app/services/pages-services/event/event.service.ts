import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(
    private router: Router,
  ) { }

  goToEvent(): void {
    this.router.navigate(['/event']);
  }
}
