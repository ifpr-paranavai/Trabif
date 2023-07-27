import { Component } from '@angular/core';
import { EventoService } from 'src/app/services/api-services/evento/evento.service';
import { PermissaoUsuarioService } from 'src/app/services/api-services/permissao-usuario/permissao-usuario.service';
import { UsuarioService } from 'src/app/services/api-services/usuario/usuario.service';

enum SelectedButton {
  Nenhum = 'Nenhum',
  Avaliadores = 'Avaliadores',
  Submissoes = 'Submissoes',
  AvaliarTrabalhos = 'AvaliarTrabalhos',
  SeusTrabalhos = 'SeusTrabalhos',
}


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent {

  constructor(
    private eventoApiService: EventoService,
    public usuarioService: UsuarioService,
    public permissaoUsuarioService: PermissaoUsuarioService
  ) {}

  selectedButton: SelectedButton = SelectedButton.Nenhum;

  abrirAvaliadores(): void {
    this.selectedButton = SelectedButton.Avaliadores;
  }

  abrirSubmissoes(): void {
    this.selectedButton = SelectedButton.Submissoes;
  }

  abrirSeusTrabalhos(): void {
    this.selectedButton = SelectedButton.SeusTrabalhos;
  }

  abrirAvaliarTrabalhos(): void {
    this.selectedButton = SelectedButton.AvaliarTrabalhos;
  }

}
