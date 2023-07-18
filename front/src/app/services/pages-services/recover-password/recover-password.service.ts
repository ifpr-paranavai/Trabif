import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RecoverPasswordService {

  constructor(
    private router: Router,
  ) { }

  goToRecover(): void {
    this.router.navigate(['/recoverPassword']);
  }
}
