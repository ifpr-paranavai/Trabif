import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(
    private router: Router,
  ) { }

  goToRegister(): void {
    this.router.navigate(['/register']);
  }
}
