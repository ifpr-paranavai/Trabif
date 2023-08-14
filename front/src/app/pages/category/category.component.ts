import { CategoriaService } from './../../services/api-services/categoria/categoria.service';
import { Component } from '@angular/core';
import { Categoria } from 'src/app/models/categoria';
import { LoginService } from 'src/app/services/pages-services/login/login.service';
import { ToastService } from 'src/app/services/pages-services/toast/toast.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent {

  categorias: Categoria[] = [];

  constructor(
    private categoriaService: CategoriaService,
    private loginService: LoginService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.getCategory();
  }

  deleteCategory(id: number): void {
    this.categoriaService.delete(id).subscribe((result) => {
      if (!result) {
        this.toastService.showSuccess("Categoria removida com sucesso!");
        this.getCategory();
      }
    });
  }

  getCategory(): void {
    this.categoriaService.getAll().subscribe((result) => {
      if (result.content) {
        this.categorias = result.content;
      }
    });
  }
}

