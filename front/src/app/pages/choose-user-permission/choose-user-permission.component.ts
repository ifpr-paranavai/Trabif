import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { PermissaoUsuario } from 'src/app/models/permissao-usuario';
import { MainService } from 'src/app/services/pages-services/main/main.service';

@Component({
  selector: 'app-choose-user-permission',
  templateUrl: './choose-user-permission.component.html',
  styleUrls: ['./choose-user-permission.component.scss']
})
export class ChooseUserPermissionComponent implements OnInit {

  permissoes: PermissaoUsuario[] = this.mainService.getUserPermission!;
  form: FormGroup = new FormGroup('');

  constructor(
    public mainService: MainService,
    private formBuilde: FormBuilder
  ) { }

  ngOnInit(): void {
    this.form = this.formBuilde.group({
      permissao: this.permissoes[0]
    });
  }

  chooseUserPermission(): void {
    localStorage.setItem('permissaoUsuarioEscolhida', JSON.stringify(this.form.value.permissao));
    this.mainService.goToMain();
  }

}
