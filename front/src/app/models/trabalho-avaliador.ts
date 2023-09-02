import { ResultadoSubmissao } from 'src/app/models/resultado-submissao';
import { Trabalho } from './trabalho';
import { Auditoria } from "./auditoria";
import { Usuario } from './usuario';

export class TrabalhoAvaliador extends Auditoria {
  id?: number;
  trabalhoDTO?: Trabalho;
  usuarioDTO?: Usuario;
  resultadoSubmissaoDTO?: ResultadoSubmissao;
}
