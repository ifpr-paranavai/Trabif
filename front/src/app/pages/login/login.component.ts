import { Component } from '@angular/core';
import { HeaderComponent } from 'src/app/components/header/header.component';
import { EventService } from 'src/app/services/pages-services/event/event.service';
import { MainService } from 'src/app/services/pages-services/main/main.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  constructor(
    public mainService: MainService,
  ) {}
}
