import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs';
import { Area } from 'src/app/models/area';
import { AreaService } from 'src/app/services/api-services/area/area.service';
import { LoginService } from 'src/app/services/pages-services/login/login.service';
import { MainService } from 'src/app/services/pages-services/main/main.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';

@Component({
  selector: 'app-area-update',
  templateUrl: './area-update.component.html',
  styleUrls: ['./area-update.component.scss']
})
export class AreaUpdateComponent implements OnInit {
  sendObj: any = new Area();
  loading = false;

  constructor(
    public activatedRoute: ActivatedRoute,
    private areaService: AreaService,
    public mainService: MainService,
    private loginService: LoginService,
    private toastService: ToastService

  ) {}
  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(map(() => window.history.state)).subscribe(res=>{
            console.log(res)
            if (!res.id) {
              this.mainService.goToMain();
            } else {
              this.sendObj = res;
            }

       });
  }

  addArea(): void {
    if (this.sendObj.descricao) {
      this.loading = true;
      this.areaService.put(this.sendObj.id, this.sendObj).subscribe((result) => {
        if (result) {
          this.mainService.goToMain();
          this.toastService.showSuccess("Área do conheimento atualizada com sucesso!");
        }
        this.loading = false;
      });
    }
  }


}
