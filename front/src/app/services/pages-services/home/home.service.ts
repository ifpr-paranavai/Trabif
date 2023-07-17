import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(
    private router: Router,
  ) { }

  goToHome(): void {
    this.router.navigate(['/home']);
  }
}
