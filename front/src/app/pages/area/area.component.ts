import { Component } from '@angular/core';
import { Area } from 'src/app/models/area';
import { AreaService } from 'src/app/services/api-services/area/area.service';
import { LoginService } from 'src/app/services/pages-services/login/login.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';

@Component({
  selector: 'app-area',
  templateUrl: './area.component.html',
  styleUrls: ['./area.component.scss']
})
export class AreaComponent {

  areas: Area[] = [];

  constructor(
    private areaService: AreaService,
    private loginService: LoginService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.getArea();
  }

  deleteArea(id: number): void {
    this.areaService.delete(id).subscribe((result) => {
      if (!result) {
        this.toastService.showSuccess("Área do conhecimento removida com sucesso!");
        this.getArea();
      }
    });
  }

  getArea(): void {
    this.areaService.getAll().subscribe((result) => {
      if (result.content) {
        this.areas = result.content;
      }
    });
  }
}

