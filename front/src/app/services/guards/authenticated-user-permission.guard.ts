import { MyEventService } from 'src/app/services/pages-services/my-event/my-event.service';
import { Injectable } from '@angular/core';
import { MainService } from '../pages-services/main/main.service';

@Injectable({
  providedIn: 'root'
})
export class authenticatedUserPermissionGuard  {
  constructor(
    public mainService: MainService,
    public myEventService: MyEventService
  ) {}
  canActivate() {
    if (this.mainService.getUserPermission) {
      return true;
    }
    this.myEventService.goToMyEvent();
    return false;
  }

}
