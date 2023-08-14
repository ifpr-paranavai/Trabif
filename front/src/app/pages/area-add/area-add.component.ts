import { Component } from '@angular/core';
import { Area } from 'src/app/models/area';
import { AreaService } from 'src/app/services/api-services/area/area.service';
import { LoginService } from 'src/app/services/pages-services/login/login.service';
import { MainService } from 'src/app/services/pages-services/main/main.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';

@Component({
  selector: 'app-area-add',
  templateUrl: './area-add.component.html',
  styleUrls: ['./area-add.component.scss']
})
export class AreaAddComponent {
  sendObj: any = new Area();
  loading = false;

  constructor(
    private areaService: AreaService,
    public mainService: MainService,
    private loginService: LoginService,
    private toastService: ToastService

  ) {}

  addArea(): void {
    if (this.sendObj.descricao) {
      this.loading = true;
      this.areaService.post(this.sendObj).subscribe((result) => {
        if (result) {
          this.mainService.goToMain();
          this.toastService.showSuccess("Área do conheimento adicionada com sucesso!");
        }
        this.loading = false;
      });
    }
  }


}
