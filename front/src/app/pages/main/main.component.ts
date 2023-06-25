import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  ngOnInit(): void {
    document.getElementById('eventos')!.style.backgroundColor = '#dadada';
  }

  retornaCorOriginalBotoes(): void {
    document.getElementById('eventos')!.style.backgroundColor = '#fafafa';
    document.getElementById('avaliadores')!.style.backgroundColor = '#fafafa';
    document.getElementById('submissoes')!.style.backgroundColor = '#fafafa';
    document.getElementById('trabalhos')!.style.backgroundColor = '#fafafa';
    document.getElementById('avaliar-trabalhos')!.style.backgroundColor = '#fafafa';
  }

  abrirAvaliadores(): void {
    this.retornaCorOriginalBotoes();
    document.getElementById('avaliadores')!.style.backgroundColor = '#dadada';
  }

  abrirSubmissoes(): void {
    this.retornaCorOriginalBotoes();
    document.getElementById('submissoes')!.style.backgroundColor = '#dadada';
  }

  abrirTrabalhos(): void {
    this.retornaCorOriginalBotoes();
    document.getElementById('trabalhos')!.style.backgroundColor = '#dadada';
  }

  abrirAvaliarTrabalhos(): void {
    this.retornaCorOriginalBotoes();
    document.getElementById('avaliar-trabalhos')!.style.backgroundColor = '#dadada';
  }

}
