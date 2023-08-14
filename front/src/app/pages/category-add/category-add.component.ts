import { CategoriaService } from './../../services/api-services/categoria/categoria.service';
import { Component } from '@angular/core';
import { Categoria } from 'src/app/models/categoria';
import { LoginService } from 'src/app/services/pages-services/login/login.service';
import { MainService } from 'src/app/services/pages-services/main/main.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';

@Component({
  selector: 'app-category-add',
  templateUrl: './category-add.component.html',
  styleUrls: ['./category-add.component.scss']
})
export class CategoryAddComponent {
  sendObj: any = new Categoria();
  loading = false;

  constructor(
    private categoriaService: CategoriaService,
    public mainService: MainService,
    private loginService: LoginService,
    private toastService: ToastService
  ) {}

  addCategory(): void {
    if (this.sendObj.descricao) {
      this.loading = true;
      this.categoriaService.post(this.sendObj).subscribe((result) => {
        if (result) {
          this.mainService.goToMain();
          this.toastService.showSuccess("Categoria adicionada com sucesso!");
        }
        this.loading = false;
      });
    }
  }


}
