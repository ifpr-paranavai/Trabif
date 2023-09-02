import { ActivatedRoute } from '@angular/router';
import { CategoriaService } from '../../services/api-services/categoria/categoria.service';
import { Component, OnInit } from '@angular/core';
import { Categoria } from 'src/app/models/categoria';
import { LoginService } from 'src/app/services/pages-services/login/login.service';
import { MainService } from 'src/app/services/pages-services/main/main.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';
import { map } from 'rxjs';

@Component({
  selector: 'app-category-update',
  templateUrl: './category-update.component.html',
  styleUrls: ['./category-update.component.scss']
})
export class CategoryUpdateComponent implements OnInit {
  sendObj: any = new Categoria();
  loading = false;

  constructor(
    public activatedRoute: ActivatedRoute,
    private categoriaService: CategoriaService,
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

  addCategory(): void {
    if (this.sendObj.descricao) {
      this.loading = true;
      this.categoriaService.put(this.sendObj.id, this.sendObj).subscribe((result) => {
        if (result) {
          this.mainService.goToMain();
          this.toastService.showSuccess("Categoria atualizada com sucesso!");
        }
        this.loading = false;
      });
    }
  }


}
