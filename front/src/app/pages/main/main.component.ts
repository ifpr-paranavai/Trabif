import { PermissaoUsuario } from 'src/app/models/permissao-usuario';
import { EventService } from './../../services/pages-services/event/event.service';
import { Component, OnInit } from '@angular/core';
import { EventoService } from 'src/app/services/api-services/evento/evento.service';
import { PermissaoUsuarioService } from 'src/app/services/api-services/permissao-usuario/permissao-usuario.service';
import { UsuarioService } from 'src/app/services/api-services/usuario/usuario.service';
import { MainService } from 'src/app/services/pages-services/main/main.service';

enum SelectedButton {
  Nenhum = 'Nenhum',
  Avaliadores = 'Avaliadores',
  Submissoes = 'Submissoes',
  AvaliarTrabalhos = 'AvaliarTrabalhos',
  MeusTrabalhos = 'MeusTrabalhos',
  Categorias = 'Categorias',
  Areas = 'Areas',
  AreasConhecimento = 'AreasConhecimento',
  Organizadores = 'Organizadores'
}


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  constructor(
    private eventoApiService: EventoService,
    public usuarioService: UsuarioService,
    public permissaoUsuarioService: PermissaoUsuarioService,
    public mainService: MainService,
    public eventService: EventService
  ) {}

  selectedButton: SelectedButton = SelectedButton.Nenhum;
  permissaoUsuarioEvento: PermissaoUsuario = new PermissaoUsuario();

  ngOnInit(): void {
    if (!this.permissaoUsuarioService.userPermissionEvent[0].id && !this.mainService.getUserPermission) {
      this.eventService.goToEvent();
    } else if (!this.permissaoUsuarioService.userPermissionEvent[0].id) {
      this.permissaoUsuarioService.userPermissionEvent = this.mainService.getUserPermission!;
      this.permissaoUsuarioEvento = this.mainService.getChosenUserPermission ?? this.permissaoUsuarioService.userPermissionEvent[0];
    } else {
      this.permissaoUsuarioEvento = this.mainService.getChosenUserPermission ?? this.permissaoUsuarioService.userPermissionEvent[0];
    }
  }

  abrirAvaliadores(): void {
    this.selectedButton = SelectedButton.Avaliadores;
  }

  abrirSubmissoes(): void {
    this.selectedButton = SelectedButton.Submissoes;
  }

  abrirCategorias(): void {
    this.selectedButton = SelectedButton.Categorias;
  }

  abrirAreas(): void {
    this.selectedButton = SelectedButton.Areas;
  }

  abrirMeusTrabalhos(): void {
    this.selectedButton = SelectedButton.MeusTrabalhos;
  }

  abrirAvaliarTrabalhos(): void {
    this.selectedButton = SelectedButton.AvaliarTrabalhos;
  }

  abrirAreasConhecimento(): void {
    this.selectedButton = SelectedButton.AreasConhecimento;
  }

  abrirOrganizadores(): void {
    this.selectedButton = SelectedButton.Organizadores;
  }

}
