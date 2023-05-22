import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class MainService {

  constructor(
    private router: Router,
  ) { }

  irParaMain(): void {
    this.router.navigate(['/main']);
  }
}
